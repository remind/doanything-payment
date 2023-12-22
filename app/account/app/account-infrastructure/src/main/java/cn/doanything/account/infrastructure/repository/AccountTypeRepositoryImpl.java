package cn.doanything.account.infrastructure.repository;

import cn.doanything.account.domain.OuterAccountType;
import cn.doanything.account.domain.repository.AccountTypeRepository;
import cn.doanything.account.infrastructure.persistence.convertor.OuterAccountTypeDalConvertor;
import cn.doanything.account.infrastructure.persistence.dataobject.OuterAccountTypeDO;
import cn.doanything.account.infrastructure.persistence.mapper.OuterAccountTypeMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * 2023/12/22
 */
@Repository
public class AccountTypeRepositoryImpl implements AccountTypeRepository {

    @Autowired
    private OuterAccountTypeDalConvertor dalConvertor;

    @Autowired
    private OuterAccountTypeMapper outerAccountTypeMapper;

    @Override
    public OuterAccountType load(String code) {
        return dalConvertor.toEntity(outerAccountTypeMapper.selectOne(getIdWrapper(code)));
    }

    private Wrapper<OuterAccountTypeDO> getIdWrapper(String code) {
        return new LambdaQueryWrapper<OuterAccountTypeDO>().eq(OuterAccountTypeDO::getCode, code);
    }
}
