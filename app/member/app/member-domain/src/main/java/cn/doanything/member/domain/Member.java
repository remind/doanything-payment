package cn.doanything.member.domain;

import cn.doanything.commons.lang.Entity;
import cn.doanything.member.types.MemberTypeEnum;
import lombok.Data;

/**
 * @author wxj
 * 2023/12/10
 */
@Data
public abstract class Member extends Entity {

    /**
     * 会员号
     */
    private String memberId;
    /**
     * 会员名称
     */
    private String memberName;
    /**
     * 会员类型
     */
    private MemberTypeEnum memberType;
}
