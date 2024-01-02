package cn.doanything.member.domain.repository;

import cn.doanything.member.domain.personal.PersonalPassword;
import cn.doanything.member.types.PasswordType;
import cn.doanything.member.types.PasswordUseType;

import java.util.List;

/**
 * @author wxj
 * 2024/1/2
 */
public interface PersonalPasswordRepository {

    void store(PersonalPassword password);
    void reStore(PersonalPassword password);

    List<PersonalPassword> load(String memberId, PasswordUseType useType);

    PersonalPassword load(String memberId, PasswordUseType useType, PasswordType type);

}
