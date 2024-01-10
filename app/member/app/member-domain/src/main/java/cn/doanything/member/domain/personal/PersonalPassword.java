package cn.doanything.member.domain.personal;

import cn.doanything.commons.lang.Entity;
import cn.doanything.member.types.PasswordStatus;
import cn.doanything.member.types.PasswordType;
import cn.doanything.member.types.PasswordUseType;
import lombok.Data;
import org.apache.commons.lang3.time.DateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 个人用户密码
 *
 * @author wxj
 * 2024/1/1
 */
@Data
public class PersonalPassword extends Entity {

    /**
     * 密码ID
     */
    private String id;

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
     * 密码
     */
    private String password;

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
    private LocalDateTime lastErrorDate;

    public void incErrorCount() {
        if (lastErrorDate != null && lastErrorDate.toLocalDate().equals(LocalDate.now())) {
            this.errorCount++;
        } else {
            this.errorCount = 1;
            this.lastErrorDate = LocalDateTime.now();
        }
    }

    public boolean clearErrorCount() {
        if (this.errorCount != 0) {
            this.errorCount = 0;
            this.lastErrorDate = null;
            return true;
        }
        return false;
    }
}
