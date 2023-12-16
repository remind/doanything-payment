package cn.doanything.account.domain.domainservice;

import cn.doanything.account.domain.Account;

/**
 * 账户管理服务
 *
 * @author wxj
 * 2023/12/16
 */
public interface AccountManagerService {

    /**
     * 开户
     *
     * @param account
     * @return
     */
    void insertAccount(Account account);

}
