package cn.doanything.account.infrastructure.repository.factory;

import cn.doanything.account.domain.repository.AccountRepository;
import cn.doanything.account.domain.repository.factory.AccountRepositoryFactory;
import cn.doanything.account.infrastructure.repository.OuterAccountRepositoryImpl;
import cn.doanything.account.types.enums.AccountFamily;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * 2023/12/21
 */
@Component
public class AccountRepositoryFactoryImpl implements AccountRepositoryFactory {
    @Resource(type = OuterAccountRepositoryImpl.class)
    private AccountRepository outerAccountRepository;

    @Override
    public AccountRepository getRepository(AccountFamily accountFamily) {
        if (accountFamily == AccountFamily.OUTER) {
            return outerAccountRepository;
        }
        return null;
    }
}
