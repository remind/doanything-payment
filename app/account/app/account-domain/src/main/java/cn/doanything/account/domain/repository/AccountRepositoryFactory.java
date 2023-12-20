package cn.doanything.account.domain.repository;

import cn.doanything.account.types.enums.AccountFamily;

/**
 * @author wxj
 * 2023/12/20
 */
public interface AccountRepositoryFactory {

    AccountRepository getAccountRepository(AccountFamily accountFamily);
}
