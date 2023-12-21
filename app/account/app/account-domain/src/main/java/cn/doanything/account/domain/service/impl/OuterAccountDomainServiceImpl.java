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
                    , outerAccountDetail.getCrDr()));
            outerAccountDetail.setBeforeBalance(account.getBalance());
            outerAccountDetail.setOuterSubAccountDetails(buildSubAccountDetail((OuterAccount) account, outerAccountDetail));
            outerAccountDetail.setAfterBalance(account.getBalance());
        });

        accountDetailRepository.store(accountDetails);
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
        List<OuterSubAccountDetail> outerSubAccountDetails = Lists.newArrayList(composeSubAccountDetail(outerAccount, outerAccountDetail, outerSubAccount));
        updateSubAccountBalance(outerSubAccount, outerSubAccountDetails);
        return outerSubAccountDetails;
    }

    private void updateSubAccountBalance(OuterSubAccount outerSubAccount, List<OuterSubAccountDetail> outerSubAccountDetails) {
        outerSubAccountDetails.forEach(outerSubAccountDetail -> {
            outerSubAccount.updateAvailableBalance(outerSubAccountDetail.getIoDirection(), outerSubAccountDetail.getAmount());
            outerSubAccountDetail.setAfterBalance(outerSubAccount.getBalance());
        });
    }

    private OuterSubAccountDetail composeSubAccountDetail(OuterAccount outerAccount, OuterAccountDetail outerAccountDetail, OuterSubAccount outerSubAccount) {
        OuterSubAccountDetail subDetail = new OuterSubAccountDetail();
        subDetail.setRequestNo(outerAccountDetail.getRequestNo());
        subDetail.setVoucherNo(outerAccountDetail.getVoucherNo());
        subDetail.setAccountNo(outerAccountDetail.getAccountNo());
        subDetail.setIoDirection(AccountUtil.convert(outerAccount.getCurrentBalanceDirection(), outerAccountDetail.getCrDr()));
        subDetail.setCrdr(outerAccountDetail.getCrDr());
        subDetail.setBeforeBalance(outerSubAccount.getBalance());
        subDetail.setAmount(outerAccountDetail.getAmount());
        subDetail.setFundType(outerSubAccount.getFundType());
        subDetail.setAccountingDate(outerAccountDetail.getAccountingDate());
        subDetail.setMemo(outerAccountDetail.getMemo());
        return subDetail;
    }
}
