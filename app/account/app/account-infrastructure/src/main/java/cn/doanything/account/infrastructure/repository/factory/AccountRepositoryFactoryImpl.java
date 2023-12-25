package cn.doanything.account.infrastructure.repository.factory;

import cn.doanything.account.domain.repository.OuterAccountRepository;
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
    private OuterAccountRepository outerAccountRepository;

    @Override
    public OuterAccountRepository getRepository(AccountFamily accountFamily) {
        if (accountFamily == AccountFamily.OUTER) {
            return outerAccountRepository;
        }
        return null;
    }
}
