package cn.doanything.member.infrastructure.persistence.repository;

import cn.doanything.member.domain.personal.PersonalPassword;
import cn.doanything.member.domain.repository.PersonalPasswordRepository;
import cn.doanything.member.infrastructure.persistence.convertor.PersonalPasswordDalConvertor;
import cn.doanything.member.infrastructure.persistence.dataobject.PersonalPasswordDO;
import cn.doanything.member.infrastructure.persistence.mapper.PersonalPasswordMapper;
import cn.doanything.member.types.PasswordType;
import cn.doanything.member.types.PasswordUseType;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wxj
 * 2024/1/2
 */
@Repository
public class PersonalPasswordRepositoryImpl implements PersonalPasswordRepository {

    @Autowired
    private PersonalPasswordMapper mapper;

    @Autowired
    private PersonalPasswordDalConvertor dalConvertor;

    @Override
    public void store(PersonalPassword password) {
        mapper.insert(dalConvertor.toDo(password));
    }

    @Override
    public void reStore(PersonalPassword password) {
        mapper.updateById(dalConvertor.toDo(password));
    }

    @Override
    public List<PersonalPassword> load(String memberId, PasswordUseType useType) {
        LambdaQueryWrapper<PersonalPasswordDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PersonalPasswordDO::getMemberId, memberId).eq(PersonalPasswordDO::getUseType, useType.getCode());
        return dalConvertor.toEntity(mapper.selectList(wrapper));
    }

    @Override
    public PersonalPassword load(String memberId, PasswordUseType useType, PasswordType type) {
        LambdaQueryWrapper<PersonalPasswordDO> wrapper = new LambdaQueryWrapper<PersonalPasswordDO>()
                .eq(PersonalPasswordDO::getMemberId, memberId)
                .eq(PersonalPasswordDO::getUseType, useType.getCode())
                .eq(PersonalPasswordDO::getType, type.getCode());
        return dalConvertor.toEntity(mapper.selectOne(wrapper));
    }
}
