package cn.doanything.member.infrastructure.persistence.repository;

import cn.doanything.commons.lang.utils.StringUtil;
import cn.doanything.framework.api.sequence.SequenceService;
import cn.doanything.member.domain.Member;
import cn.doanything.member.domain.config.MemberConstants;
import cn.doanything.member.domain.personal.PersonalMember;
import cn.doanything.member.domain.repository.MemberRepository;
import cn.doanything.member.infrastructure.persistence.convertor.MemberDalConvertor;
import cn.doanything.member.infrastructure.persistence.dataobject.MemberDO;
import cn.doanything.member.infrastructure.persistence.dataobject.PersonalMemberDO;
import cn.doanything.member.infrastructure.persistence.mapper.MemberMapper;
import cn.doanything.member.infrastructure.persistence.mapper.PersonalMemberMapper;
import cn.doanything.member.types.MemberType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 会员仓储实现
 *
 * @author wxj
 * 2023/12/10
 */
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private MemberDalConvertor memberDalConvertor;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private PersonalMemberMapper personalMemberMapper;

    @Override
    public void store(Member member) {
        memberMapper.insert(memberDalConvertor.toMemberDo(member));
        if (member instanceof PersonalMember) {
            personalMemberMapper.insert(memberDalConvertor.toPersonalMemberDo((PersonalMember) member));
        }
    }

    @Override
    public Member load(String memberId) {
        MemberDO memberDO = memberMapper.selectById(memberId);
        if (memberDO != null) {
            if (MemberType.PERSONAL.getCode() == memberDO.getMemberType()) {
                PersonalMemberDO personalMemberDO = personalMemberMapper.selectById(memberDO.getMemberId());
                return memberDalConvertor.toPersonalMember(memberDO, personalMemberDO);
            }
        }
        return null;
    }

    @Override
    public String genMemberId(MemberType memberType) {
        Long seq = sequenceService.getNext(MemberConstants.MEMBER_ID_SEQ_NAME);
        return memberType.getPrefix() + StringUtil.alignRight(String.valueOf(seq), MemberConstants.MEMBER_ID_SEQ_LENGTH,
                MemberConstants.MEMBER_ID_FIX_CHAR);
    }

}
