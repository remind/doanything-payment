package cn.doanything.member.domain;

import cn.doanything.commons.lang.Entity;
import cn.doanything.member.types.MemberStatus;
import cn.doanything.member.types.MemberType;
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
     * 会员状态
     */
    private MemberStatus status;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 获取会员类型
     */
    public abstract MemberType getMemberType();
}
