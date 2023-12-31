package cn.doanything.member.domain.personal.domainservice.impl;

import cn.doanything.member.domain.personal.PersonalMember;
import cn.doanything.member.domain.personal.domainservice.PersonalService;
import cn.doanything.member.domain.repository.MemberRepository;
import cn.doanything.member.types.MemberStatus;
import cn.doanything.member.types.MemberType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2023/12/10
 */
@Service
public class PersonalServiceImpl implements PersonalService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void create(PersonalMember personalMember) {
        personalMember.setMemberId(memberRepository.genMemberId(MemberType.PERSONAL));
        personalMember.setStatus(MemberStatus.NORMAL);
        memberRepository.store(personalMember);
    }
}
