package cn.doanything.member.domain.personal.service;

import cn.doanything.member.domain.personal.PersonalMember;

/**
 * 新增个人用户
 * @author wxj
 * 2023/12/10
 */
public interface PersonalDomainService {

    /**
     * 创建个人用户
     * @param personalMember
     * @return
     */
    void create(PersonalMember personalMember);

}
