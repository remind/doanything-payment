package cn.doanything.member.types;

import cn.doanything.commons.enums.EnableEnum;
import cn.doanything.commons.lang.Entity;
import lombok.Data;

/**
 * @author wxj
 * 2024/1/4
 */
@Data
public class MemberAccount extends Entity {
    private Long id;

    /**
     * 会员编号
     */
    private String memberId;

    /**
     * 账户类型
     */
    private String accountType;

    /**
     * 账户类型
     */
    private String accountNo;

    /**
     * 状态(0:禁用，1启用)
     */
    private EnableEnum status;

}
