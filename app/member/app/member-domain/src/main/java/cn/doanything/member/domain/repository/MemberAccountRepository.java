package cn.doanything.member.domain.repository;

import cn.doanything.member.types.MemberAccount;

import java.util.List;

/**
 * @author wxj
 * 2024/1/4
 */
public interface MemberAccountRepository {

    /**
     * 存储会员账户
     * @param memberAccount
     */
    void store(MemberAccount memberAccount);

    /**
     * 存储会员账户
     * @param memberAccounts
     */
    void store(List<MemberAccount> memberAccounts);
}
