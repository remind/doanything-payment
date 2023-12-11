package cn.doanything.member.facade.personal;

import cn.doanything.member.domain.Member;
import cn.doanything.member.domain.personal.PersonalMember;
import cn.doanything.member.domain.personal.domainservice.PersonalService;
import cn.doanything.member.domain.repository.MemberRepository;
import cn.doanything.member.facade.personal.convertor.PersonalMemberDtoConvertor;
import cn.doanything.member.facade.personal.dto.PersonalAddRequest;
import cn.doanything.member.facade.personal.dto.PersonalDetailInfo;
import cn.doanything.member.types.MemberType;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 个人会员相关接口实现
 * @author wxj
 * 2023/12/11
 */
@DubboService
public class PersonalManageFacadeImpl implements PersonalManageFacade {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PersonalService personalService;

    @Autowired
    private PersonalMemberDtoConvertor personalMemberDtoConvertor;

    @Override
    public PersonalDetailInfo create(PersonalAddRequest request) {
        PersonalMember personalMember = personalMemberDtoConvertor.toPersonalMember(request);
        personalService.create(personalMember);
        return personalMemberDtoConvertor.toPersonalDetailInfo(personalMember);
    }

    @Override
    public PersonalDetailInfo query(String memberId) {
        Member member = memberRepository.load(memberId);
        if (member != null && MemberType.PERSONAL.equals(member.getMemberType())) {
            return personalMemberDtoConvertor.toPersonalDetailInfo((PersonalMember) member);
        }
        return null;
    }
}
