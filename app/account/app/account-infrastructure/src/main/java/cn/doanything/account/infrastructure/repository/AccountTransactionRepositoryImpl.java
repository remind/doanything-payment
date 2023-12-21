package cn.doanything.account.infrastructure.repository;

import cn.doanything.account.domain.AccountTransaction;
import cn.doanything.account.domain.repository.AccountTransactionRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * 2023/12/21
 */
@Repository
public class AccountTransactionRepositoryImpl implements AccountTransactionRepository {
    @Override
    public void store(AccountTransaction accountTransaction) {

    }
}
