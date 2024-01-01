package cn.doanything.member.domain.personal;

import cn.doanything.commons.lang.Entity;
import cn.doanything.member.types.PasswordStatus;
import cn.doanything.member.types.PasswordType;
import cn.doanything.member.types.PasswordUseType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 个人用户密码
 * @author wxj
 * 2024/1/1
 */
@Data
public class PersonalPassword extends Entity {
    /**
     * 会员id
     */
    private String memberId;

    /**
     * 密码类型，如登录密码，支付密码
     */
    private PasswordUseType useType;

    /**
     * 密码子类型，如文本、手势、指纹
     */
    private PasswordType type;

    /**
     * 状态
     */
    private PasswordStatus status;

    /**
     * 锁定结束时间
     */
    private LocalDateTime lockEndTime;

    /**
     * 错误次数
     */
    private int errorCount;

    /**
     * 上次错误日期
     */
    private Date lastErrorDate;
}
