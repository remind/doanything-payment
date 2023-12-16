package cn.doanything.account.application.processor.impl;

import cn.doanything.account.application.processor.AccountOperationGroup;
import cn.doanything.account.application.processor.UpdateBalanceProcessor;
import cn.doanything.account.domain.Account;
import cn.doanything.account.domain.AccountResultCode;
import cn.doanything.account.domain.detail.AccountDetail;
import cn.doanything.account.domain.repository.AccountRepository;
import cn.doanything.account.domain.utils.AccountUtil;
import cn.doanything.account.infrastructure.repository.OuterAccountRepositoryImpl;
import cn.doanything.commons.exceptions.BaseException;
import cn.doanything.commons.lang.utils.AssertUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;


/**
 * @author wxj
 * 2023/12/16
 */
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
        });
    }
}
