package cn.doanything.infrastructure.persistence.repository;

import cn.doanything.commons.lang.utils.StringUtil;
import cn.doanything.framework.api.sequence.SequenceService;
import cn.doanything.infrastructure.persistence.mapper.PersonalMemberMapper;
import cn.doanything.member.domain.Member;
import cn.doanything.member.domain.config.MemberConstants;
import cn.doanything.member.domain.repository.MemberRepository;
import cn.doanything.member.types.MemberTypeEnum;
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
    private PersonalMemberMapper personalMemberMapper;

    @Override
    public void store(Member member) {
        String memberId = genMemberId(member.getMemberType());
        if (member instanceof  PersonalMemberMapper) {
//            personalMemberMapper.insert();
        }
    }

    @Override
    public String genMemberId(MemberTypeEnum memberTypeEnum) {
        Long seq = sequenceService.getNext(MemberConstants.MEMBER_ID_SEQ_NAME);
        return memberTypeEnum.getPrefix() + StringUtil.alignRight(String.valueOf(seq), MemberConstants.MEMBER_ID_SEQ_LENGTH,
                MemberConstants.MEMBER_ID_FIX_CHAR);
    }

}
