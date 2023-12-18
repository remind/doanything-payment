package cn.doanything.account.application.processor.impl;

import cn.doanything.account.application.processor.AccountOperationGroup;
import cn.doanything.account.application.processor.UpdateBalanceProcessor;
import cn.doanything.account.domain.*;
import cn.doanything.account.domain.detail.AccountDetail;
import cn.doanything.account.domain.detail.SubAccountDetail;
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
import java.util.Map;


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

        operationGroup.getDetails().forEach(accountDetail -> {
            accountDetail.setIoDirection(AccountUtil.convert(account.getCurrentBalanceDirection()
                    , accountDetail.getCrdr()));
            accountDetail.setBeforeAmount(account.getBalance());
            accountDetail.setSubAccountDetails(buildSubAccountDetail((OuterAccount) account, accountDetail));
            updateSubAccountBalance((OuterAccount) account, accountDetail.getSubAccountDetails());
            accountDetail.setAfterAmount(account.getBalance());
        });
    }

    private void updateSubAccountBalance(OuterAccount outerAccount, List<SubAccountDetail> subAccountDetails) {
        subAccountDetails.forEach(subAccountDetail -> {
            AccountUtil.changeBalance(outerAccount.getAvailableBalance(), subAccountDetail.getIoDirection(), subAccountDetail.getAmount());
            AccountUtil.changeBalance(outerAccount.getBalance(), subAccountDetail.getIoDirection(), subAccountDetail.getAmount());
            subAccountDetail.setAfterAmount(outerAccount.getBalance());
        });
    }

    /**
     * 一次有可能操作多个子户的，预留支持多子户明细，一个子户只能有一条明细
     * @param outerAccount
     * @param accountDetail
     * @return
     */
    private List<SubAccountDetail> buildSubAccountDetail(OuterAccount outerAccount, AccountDetail accountDetail) {
        OuterSubAccount outerSubAccount;
        Map<String, OuterSubAccount> subAccountMap = outerAccount.getSubAccountMap();
        if (StringUtils.isNotBlank(accountDetail.getFundType())) {
            outerSubAccount = subAccountMap.get(accountDetail.getFundType());
        } else {
            outerSubAccount = subAccountMap.get(AccountDomainConstants.DEFAULT_FUND_TYPE);
        }
        AssertUtil.isNotNull(outerSubAccount, AccountResultCode.SUB_ACCOUNT_NOT_EXISTS);
        return Lists.newArrayList(composeSubAccountDetail(outerAccount, accountDetail, outerSubAccount));
    }

    private SubAccountDetail composeSubAccountDetail(OuterAccount outerAccount, AccountDetail accountDetail, OuterSubAccount outerSubAccount) {
        SubAccountDetail subDetail = new SubAccountDetail();
        subDetail.setVoucherNo(accountDetail.getVoucherNo());
        subDetail.setSystemTraceNo(accountDetail.getSystemTraceNo());
        subDetail.setAccountNo(accountDetail.getAccountNo());
        subDetail.setIoDirection(AccountUtil.convert(outerAccount.getCurrentBalanceDirection(), accountDetail.getCrdr()));
        subDetail.setCrdr(accountDetail.getCrdr());
        subDetail.setAmount(accountDetail.getAmount());
        subDetail.setFundType(outerSubAccount.getFundType());
        subDetail.setRemark(accountDetail.getRemark());
        return subDetail;
    }
}
