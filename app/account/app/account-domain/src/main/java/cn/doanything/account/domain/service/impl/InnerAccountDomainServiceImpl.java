package cn.doanything.account.domain.service.impl;

import cn.doanything.account.domain.InnerAccount;
import cn.doanything.account.domain.detail.AccountDetail;
import cn.doanything.account.domain.detail.InnerAccountDetail;
import cn.doanything.account.domain.repository.AccountDetailRepository;
import cn.doanything.account.domain.repository.InnerAccountRepository;
import cn.doanything.account.domain.service.InnerAccountDomainService;
import cn.doanything.account.domain.utils.AccountUtil;
import cn.doanything.account.types.AccountResultCode;
import cn.doanything.account.types.enums.IODirection;
import cn.doanything.commons.exceptions.BizException;
import cn.doanything.commons.lang.utils.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wxj
 * 2023/12/25
 */
@Component
@Slf4j
public class InnerAccountDomainServiceImpl implements InnerAccountDomainService {

    @Autowired
    private InnerAccountRepository accountRepository;

    @Autowired
    private AccountDetailRepository accountDetailRepository;

    @Override
    public void changeBalance(String accountNo, List<AccountDetail> details) {
        InnerAccount account;
        try {
            account = accountRepository.lock(accountNo);
        } catch (Exception e) {
            log.error("账户锁定异常,accountNo=" + accountNo, e);
            throw new BizException(AccountResultCode.ACCOUNT_LOCK_TIME_OUT);
        }
        AssertUtil.isNotNull(account, AccountResultCode.ACCOUNT_ID_NOT_EXISTS);

        details.forEach(accountDetail -> {
            InnerAccountDetail detail = (InnerAccountDetail) accountDetail;
            detail.setIoDirection(AccountUtil.convert(account.getCurrentBalanceDirection()
                    , detail.getCrDr()));
            checkBalance(account, detail);
            updateBalance(account, detail);
        });
        accountDetailRepository.store(details);
        accountRepository.reStore(account);
    }

    private void checkBalance(InnerAccount account, InnerAccountDetail detail) {
        if (detail.getIoDirection() == IODirection.OUT) {
            AssertUtil.isFalse(detail.getAmount().greaterThan(account.getBalance())
                    , AccountResultCode.ACCOUNT_BALANCE_NOT_ENOUGH);
        }
    }

    private void updateBalance(InnerAccount account, InnerAccountDetail detail) {
        detail.setBeforeBalance(account.getBalance());
        account.updateBalance(detail.getIoDirection(), detail.getAmount());
        detail.setAfterBalance(account.getBalance());
    }
}
