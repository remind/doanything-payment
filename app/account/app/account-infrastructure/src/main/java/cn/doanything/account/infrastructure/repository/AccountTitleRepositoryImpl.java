package cn.doanything.account.infrastructure.repository;

import cn.doanything.account.domain.accounting.AccountTitle;
import cn.doanything.account.domain.repository.AccountTitleRepository;
import cn.doanything.account.infrastructure.persistence.convertor.AccountTitleDalConvertor;
import cn.doanything.account.infrastructure.persistence.dataobject.AccountTitleDO;
import cn.doanything.account.infrastructure.persistence.dataobject.OuterAccountTypeDO;
import cn.doanything.account.infrastructure.persistence.mapper.AccountTitleMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author wxj
 * 2023/12/16
 */
@Repository
public class AccountTitleRepositoryImpl implements AccountTitleRepository {

    @Resource
    private AccountTitleMapper accountTitleMapper;

    @Resource
    private AccountTitleDalConvertor dalConvertor;

    @Override
    public void store(AccountTitle accountTitle) {
        accountTitleMapper.insert(dalConvertor.toDo(accountTitle));
    }

    @Override
    public void reStore(AccountTitle accountTitle) {
        accountTitleMapper.updateById(dalConvertor.toDo(accountTitle));
    }

    @Override
    public AccountTitle load(String titleCode) {
        return dalConvertor.toEntity(accountTitleMapper.selectOne(getIdWrapper(titleCode)));
    }

    @Override
    public AccountTitle lock(String titleCode) {
        return null;
    }

    private Wrapper<AccountTitleDO> getIdWrapper(String titleCode) {
        return new LambdaQueryWrapper<AccountTitleDO>().eq(AccountTitleDO::getTitleCode, titleCode);
    }
}
