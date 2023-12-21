package cn.doanything.account.domain.repository.factory;

import cn.doanything.account.domain.repository.AccountRepository;
import cn.doanything.account.types.enums.AccountFamily;

/**
 * @author wxj
 * 2023/12/20
 */
public interface AccountRepositoryFactory {

    AccountRepository getRepository(AccountFamily accountFamily);
}
