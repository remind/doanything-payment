package cn.doanything.account.domain.repository;

import cn.doanything.account.domain.AccountTransaction;

/**
 * @author wxj
 * 2023/12/21
 */
public interface AccountTransactionRepository {

    void store(AccountTransaction accountTransaction);
}
