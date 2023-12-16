package cn.doanything.account.infrastructure.repository;

import cn.doanything.account.domain.Account;
import cn.doanything.account.domain.repository.AccountRepository;
import cn.doanything.account.types.enums.AccountFamily;
import org.springframework.stereotype.Repository;

/**
 * 账户仓储实现
 * @author wxj
 * 2023/12/16
 */
@Repository
public class OuterAccountRepositoryImpl implements AccountRepository {
    @Override
    public void store(Account account) {

    }

    @Override
    public void reStore(Account account) {

    }

    @Override
    public Account load(String memberId) {
        return null;
    }

    @Override
    public Account lock(String memberId) {
        return null;
    }
}
