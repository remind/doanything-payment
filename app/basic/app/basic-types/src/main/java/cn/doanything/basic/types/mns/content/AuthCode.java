package cn.doanything.basic.types.mns.content;

import cn.doanything.commons.enums.EnableEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 短信验证码
 *
 * @author wxj
 * 2024/1/7
 */
@Data
public class AuthCode {

    /**
     * 验证码内容
     */
    private String authCode;

    /**
     * 消息文本
     */
    private String messageText;

    /**
     * 验证状态,可验证、失效
     */
    private EnableEnum authStatus;

    /**
     * 失效原因，超时、重复生成、验证通过
     */
    private String invalidReason;

    /**
     * 已验证次数
     */
    private Integer verifiedCount = 0;

    /**
     * 可被验证次数
     */
    private Integer verifiableCount;

    /**
     * 有效分钟数
     */
    private int validMinute;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public void incVerifiedCount() {
        this.verifiedCount++;
        if (this.verifiedCount >= this.verifiableCount) {
            invalid("验证次数超限");
        }
    }

    public void invalid(String reason) {
        this.authStatus = EnableEnum.DISABLE;
        this.invalidReason = reason;
    }
}
