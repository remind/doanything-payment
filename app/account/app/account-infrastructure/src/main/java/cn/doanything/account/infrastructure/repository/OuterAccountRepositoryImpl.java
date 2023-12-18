package cn.doanything.account.infrastructure.repository;

import cn.doanything.account.domain.Account;
import cn.doanything.account.domain.OuterAccount;
import cn.doanything.account.domain.repository.AccountRepository;
import cn.doanything.account.infrastructure.persistence.convertor.OuterAccountDalConvertor;
import cn.doanything.account.infrastructure.persistence.dataobject.OuterAccountDO;
import cn.doanything.account.infrastructure.persistence.dataobject.OuterSubAccountDO;
import cn.doanything.account.infrastructure.persistence.mapper.OuterAccountMapper;
import cn.doanything.account.infrastructure.persistence.mapper.OuterSubAccountMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户仓储实现
 * @author wxj
 * 2023/12/16
 */
@Repository
public class OuterAccountRepositoryImpl implements AccountRepository {

    @Autowired
    private OuterAccountMapper outerAccountMapper;

    @Autowired
    private OuterSubAccountMapper outerSubAccountMapper;

    @Autowired
    private OuterAccountDalConvertor dalConvertor;

    @Override
    public void store(Account account) {

    }

    @Override
    public void reStore(Account account) {

    }

    @Override
    public Account load(String accountNo) {
        return null;
    }

    @Override
    public Account lock(String accountNo) {
        OuterAccount outerAccount = null;
        OuterAccountDO outerAccountDO = outerAccountMapper.lockById(accountNo);
        if (outerAccountDO != null) {
            List<OuterSubAccountDO> outerSubAccountDOS = outerSubAccountMapper.selectList(Wrappers.lambdaQuery(OuterSubAccountDO.class)
                    .eq(OuterSubAccountDO::getAccountNo, outerAccountDO.getAccountNo()));


            outerAccount = dalConvertor.toEntity(outerAccountDO);

        }
        return outerAccount;
    }
}
