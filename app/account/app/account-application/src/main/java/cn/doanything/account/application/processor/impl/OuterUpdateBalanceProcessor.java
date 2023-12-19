package cn.doanything.account.application.processor.impl;

import cn.doanything.account.application.processor.AccountOperationGroup;
import cn.doanything.account.application.processor.UpdateBalanceProcessor;
import cn.doanything.account.domain.Account;
import cn.doanything.account.domain.AccountDomainConstants;
import cn.doanything.account.domain.OuterAccount;
import cn.doanything.account.domain.OuterSubAccount;
import cn.doanything.account.domain.detail.OuterAccountDetail;
import cn.doanything.account.domain.detail.OuterSubAccountDetail;
import cn.doanything.account.domain.repository.AccountRepository;
import cn.doanything.account.domain.utils.AccountUtil;
import cn.doanything.account.infrastructure.repository.OuterAccountRepositoryImpl;
import cn.doanything.account.types.AccountResultCode;
import cn.doanything.commons.exceptions.BaseException;
import cn.doanything.commons.lang.utils.AssertUtil;
import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author wxj
 * 2023/12/16
 */
@Component
@Slf4j
public class OuterUpdateBalanceProcessor implements UpdateBalanceProcessor {

    @Resource(type = OuterAccountRepositoryImpl.class)
    private AccountRepository accountRepository;

    @Override
    public void update(AccountOperationGroup operationGroup) {
        Account account;
        try {
            account = accountRepository.lock(operationGroup.getAccountNo());
        } catch (Exception e) {
            log.error("账户锁定异常,accountNo={}", operationGroup.getAccountNo());
            throw new BaseException(AccountResultCode.ACCOUNT_LOCK_TIME_OUT);
        }
        AssertUtil.isNotNull(account, AccountResultCode.ACCOUNT_ID_NOT_EXISTS);

        operationGroup.getDetails().forEach(outerAccountDetail -> {
            outerAccountDetail.setIoDirection(AccountUtil.convert(account.getCurrentBalanceDirection()
                    , outerAccountDetail.getCrdr()));
            outerAccountDetail.setBeforeBalance(account.getBalance());
            outerAccountDetail.setOuterSubAccountDetails(buildSubAccountDetail((OuterAccount) account, outerAccountDetail));
            updateSubAccountBalance((OuterAccount) account, outerAccountDetail.getOuterSubAccountDetails());
            outerAccountDetail.setAfterBalance(account.getBalance());
        });
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
