package cn.doanything.account.domain.domainservice.impl;

import cn.doanything.account.domain.Account;
import cn.doanything.account.domain.domainservice.AccountManagerService;
import cn.doanything.account.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * 2023/12/16
 */
@Component
public class AccountManagerServiceImpl implements AccountManagerService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void insertAccount(Account account) {
        accountRepository.store(account);
    }
}
