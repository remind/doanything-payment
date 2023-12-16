package cn.doanything.account.infrastructure.repository;

import cn.doanything.account.domain.accounting.AccountTitle;
import cn.doanything.account.domain.repository.AccountTitleRepository;
import cn.doanything.account.infrastructure.persistence.convertor.AccountTitleDalConvertor;
import cn.doanything.account.infrastructure.persistence.mapper.AccountTitleMapper;
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
        return dalConvertor.toEntity(accountTitleMapper.selectById(titleCode));
    }

    @Override
    public AccountTitle lock(String titleCode) {
        return null;
    }
}
