package cn.doanything.basic.infrastructure.persistence.mns.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 验证码
 * </p>
 *
 * @author wxj
 * @since 2024-01-06
 */
@TableName("tm_message_auth_code")
public class MessageAuthCodeDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    @TableId("message_id")
    private Long messageId;

    /**
     * 验证码内容
     */
    private String authCode;

    /**
     * 验证状态,可验证、失效
     */
    private String authStatus;

    /**
     * 失效原因，超时、重复生成、验证通过
     */
    private String invalidReason;

    /**
     * 已验证次数
     */
    private Integer verifiedCount;

    /**
     * 可被验证次数
     */
    private Integer verifiableCount;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 最后修改时间
     */
    private LocalDateTime gmtModified;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    public String getInvalidReason() {
        return invalidReason;
    }

    public void setInvalidReason(String invalidReason) {
        this.invalidReason = invalidReason;
    }

    public Integer getVerifiedCount() {
        return verifiedCount;
    }

    public void setVerifiedCount(Integer verifiedCount) {
        this.verifiedCount = verifiedCount;
    }

    public Integer getVerifiableCount() {
        return verifiableCount;
    }

    public void setVerifiableCount(Integer verifiableCount) {
        this.verifiableCount = verifiableCount;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "MessageAuthCodeDO{" +
        "messageId = " + messageId +
        ", authCode = " + authCode +
        ", authStatus = " + authStatus +
        ", invalidReason = " + invalidReason +
        ", verifiedCount = " + verifiedCount +
        ", verifiableCount = " + verifiableCount +
        ", expireTime = " + expireTime +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        "}";
    }
}
