package cn.doanything.account.infrastructure.repository;

import cn.doanything.account.domain.repository.AccountRepository;
import cn.doanything.account.domain.repository.AccountRepositoryFactory;
import cn.doanything.account.types.enums.AccountFamily;
import jakarta.annotation.Resource;

/**
 * @author wxj
 * 2023/12/20
 */
public class AccountRepositoryFactoryImpl implements AccountRepositoryFactory {

    @Resource(type = OuterAccountRepositoryImpl.class)
    private AccountRepository outerAccountRepository;
    @Override
    public AccountRepository getAccountRepository(AccountFamily accountFamily) {
        if (accountFamily == AccountFamily.OUTER) {
            return outerAccountRepository;
        }
        return null;
    }
}
