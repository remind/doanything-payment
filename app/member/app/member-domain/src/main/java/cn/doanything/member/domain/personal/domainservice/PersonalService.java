package cn.doanything.member.domain.personal.domainservice;

import cn.doanything.member.domain.personal.PersonalMember;

/**
 * 新增个人用户
 * @author wxj
 * 2023/12/10
 */
public interface PersonalService {

    void create(PersonalMember personalMember);
}
