package cn.doanything.member.domain.personal.domainservice.impl;

import cn.doanything.member.domain.personal.PersonalMember;
import cn.doanything.member.domain.personal.domainservice.PersonalService;
import cn.doanything.member.domain.repository.MemberRepository;

/**
 * @author wxj
 * 2023/12/10
 */
public class PersonalServiceImpl implements PersonalService {

    private MemberRepository memberRepository;

    @Override
    public void create(PersonalMember personalMember) {
        memberRepository.store(personalMember);
    }
}
