package cn.doanything.member.domain.repository;

import cn.doanything.member.domain.Member;
import cn.doanything.member.types.MemberTypeEnum;

/**
 * @author wxj
 * 2023/12/10
 */
public interface MemberRepository {

    /**
     * 新增会员
     * @param member
     */
    void store(Member member);

    /**
     * 生成会员ID
     * @param memberTypeEnum
     * @return
     */
    String genMemberId(MemberTypeEnum memberTypeEnum);
}
