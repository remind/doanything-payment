package cn.doanything.member.domain.repository;

import cn.doanything.member.domain.personal.PersonalPassword;
import cn.doanything.member.types.PasswordUseType;

import java.util.List;

/**
 * @author wxj
 * 2024/1/2
 */
public interface PersonalPasswordRepository {

    void reStore(PersonalPassword password);

    void reStore(List<PersonalPassword> passwords);

    List<PersonalPassword> load(String memberId, PasswordUseType useType);

}
