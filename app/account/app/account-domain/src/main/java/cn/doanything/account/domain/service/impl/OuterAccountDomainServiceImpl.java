package cn.doanything.account.domain.service.impl;

import cn.doanything.account.domain.Account;
import cn.doanything.account.domain.AccountDomainConstants;
import cn.doanything.account.domain.OuterAccount;
import cn.doanything.account.domain.OuterSubAccount;
import cn.doanything.account.domain.detail.AccountDetail;
import cn.doanything.account.domain.detail.OuterAccountDetail;
import cn.doanything.account.domain.detail.OuterSubAccountDetail;
import cn.doanything.account.domain.repository.AccountDetailRepository;
import cn.doanything.account.domain.repository.factory.AccountRepositoryFactory;
import cn.doanything.account.domain.service.OuterAccountDomainService;
import cn.doanything.account.domain.utils.AccountUtil;
import cn.doanything.account.types.AccountResultCode;
import cn.doanything.account.types.enums.AccountFamily;
import cn.doanything.commons.exceptions.BaseException;
import cn.doanything.commons.lang.utils.AssertUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wxj
 * 2023/12/20
 */
@Component
@Slf4j
public class OuterAccountDomainServiceImpl implements OuterAccountDomainService {

    @Autowired
    private AccountRepositoryFactory accountRepositoryFactory;

    @Autowired
    private AccountDetailRepository accountDetailRepository;

    @Override
    public void changeBalance(String accountNo, List<AccountDetail> accountDetails) {
        Account account;
        try {
            account = accountRepositoryFactory.getRepository(AccountFamily.OUTER).lock(accountNo);
        } catch (Exception e) {
            log.error("账户锁定异常,accountNo=" + accountNo, e);
            throw new BaseException(AccountResultCode.ACCOUNT_LOCK_TIME_OUT);
        }
        AssertUtil.isNotNull(account, AccountResultCode.ACCOUNT_ID_NOT_EXISTS);

        accountDetails.forEach(accountDetail -> {
            OuterAccountDetail outerAccountDetail = (OuterAccountDetail) accountDetail;
            outerAccountDetail.setIoDirection(AccountUtil.convert(account.getCurrentBalanceDirection()
                    , outerAccountDetail.getCrdr()));
            outerAccountDetail.setBeforeBalance(account.getBalance());
            outerAccountDetail.setOuterSubAccountDetails(buildSubAccountDetail((OuterAccount) account, outerAccountDetail));
            updateSubAccountBalance((OuterAccount) account, outerAccountDetail.getOuterSubAccountDetails());
            outerAccountDetail.setAfterBalance(account.getBalance());
        });

        accountDetailRepository.store(accountDetails);
    }

    private void updateSubAccountBalance(OuterAccount outerAccount, List<OuterSubAccountDetail> outerSubAccountDetails) {
        outerSubAccountDetails.forEach(outerSubAccountDetail -> {
            AccountUtil.changeBalance(outerAccount.getAvailableBalance(), outerSubAccountDetail.getIoDirection(), outerSubAccountDetail.getAmount());
            AccountUtil.changeBalance(outerAccount.getBalance(), outerSubAccountDetail.getIoDirection(), outerSubAccountDetail.getAmount());
            outerSubAccountDetail.setAfterBalance(outerAccount.getBalance());
        });
    }

    /**
     * 一次有可能操作多个子户的，预留支持多子户明细，一个子户只能有一条明细
     *
     * @param outerAccount
     * @param outerAccountDetail
     * @return
     */
    private List<OuterSubAccountDetail> buildSubAccountDetail(OuterAccount outerAccount, OuterAccountDetail outerAccountDetail) {
        String fundType = StringUtils.isNotBlank(outerAccountDetail.getFundType()) ? outerAccountDetail.getFundType() : AccountDomainConstants.DEFAULT_FUND_TYPE;
        OuterSubAccount outerSubAccount = outerAccount.getSubAccountByFundType(fundType);
        AssertUtil.isNotNull(outerSubAccount, AccountResultCode.SUB_ACCOUNT_NOT_EXISTS);
        return Lists.newArrayList(composeSubAccountDetail(outerAccount, outerAccountDetail, outerSubAccount));
    }

    private OuterSubAccountDetail composeSubAccountDetail(OuterAccount outerAccount, OuterAccountDetail outerAccountDetail, OuterSubAccount outerSubAccount) {
        OuterSubAccountDetail subDetail = new OuterSubAccountDetail();
        subDetail.setVoucherNo(outerAccountDetail.getVoucherNo());
        subDetail.setAccountNo(outerAccountDetail.getAccountNo());
        subDetail.setIoDirection(AccountUtil.convert(outerAccount.getCurrentBalanceDirection(), outerAccountDetail.getCrdr()));
        subDetail.setCrdr(outerAccountDetail.getCrdr());
        subDetail.setAmount(outerAccountDetail.getAmount());
        subDetail.setFundType(outerSubAccount.getFundType());
        subDetail.setMemo(outerAccountDetail.getMemo());
        return subDetail;
    }
}
