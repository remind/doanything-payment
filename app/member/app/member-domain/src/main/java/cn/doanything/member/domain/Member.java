package cn.doanything.member.domain;

import cn.doanything.member.types.MemberTypeEnum;
import lombok.Data;

/**
 * @author wxj
 * 2023/12/10
 */
@Data
public abstract class Member {

    /**
     * 会员号
     */
    private String memberId;
    /**
     * 会员名称
     */
    private String memberName;
    /**
     * 会员简称
     */
    private String memberShortName;
    /**
     * 会员类型
     */
    private MemberTypeEnum memberType;
}
