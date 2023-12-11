package cn.doanything.member.domain.repository;

import cn.doanything.member.domain.Member;
import cn.doanything.member.types.MemberType;

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
     * 加载会员
     * @param memberId
     * @return
     */
    Member load(String memberId);

    /**
     * 生成会员ID
     * @param memberType
     * @return
     */
    String genMemberId(MemberType memberType);
}
