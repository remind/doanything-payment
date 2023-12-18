package cn.doanything.account.domain.repository;

import cn.doanything.account.domain.Account;
import cn.doanything.account.types.enums.AccountFamily;

/**
 * 账户仓储
 * @author wxj
 * 2023/12/16
 */
public interface AccountRepository {

    void store(Account account);

    void reStore(Account account);

    Account load(String accountNo);

    Account lock(String accountNo);

}
