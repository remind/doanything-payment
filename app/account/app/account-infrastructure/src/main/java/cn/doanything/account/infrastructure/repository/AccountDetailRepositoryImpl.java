package cn.doanything.account.infrastructure.repository;

import cn.doanything.account.domain.detail.AccountDetail;
import cn.doanything.account.domain.repository.AccountDetailRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wxj
 * 2023/12/21
 */
@Repository
public class AccountDetailRepositoryImpl implements AccountDetailRepository {
    @Override
    public void store(List<AccountDetail> accountDetails) {

    }
}
