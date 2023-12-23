package cn.doanything.account.infrastructure.repository;

import cn.doanything.account.domain.AccountTransaction;
import cn.doanything.account.domain.repository.AccountTransactionRepository;
import cn.doanything.account.infrastructure.persistence.convertor.AccountTransactionDalConvertor;
import cn.doanything.account.infrastructure.persistence.mapper.AccountTransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * 2023/12/21
 */
@Repository
public class AccountTransactionRepositoryImpl implements AccountTransactionRepository {

    @Autowired
    private AccountTransactionDalConvertor dalConvertor;

    @Autowired
    private AccountTransactionMapper mapper;

    @Override
    public void store(AccountTransaction accountTransaction) {
        mapper.insert(dalConvertor.toDo(accountTransaction));
    }
}
