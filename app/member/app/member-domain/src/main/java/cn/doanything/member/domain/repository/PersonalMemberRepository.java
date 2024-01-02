package cn.doanything.member.domain.repository;

import cn.doanything.member.domain.personal.PersonalMember;

/**
 * @author wxj
 * 2024/1/2
 */
public interface PersonalMemberRepository {

    PersonalMember loadByLoginName(String loginName);
}
