package cn.doanything.basic.infrastructure.persistence.mns.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 消息明细
 * </p>
 *
 * @author wxj
 * @since 2024-01-09
 */
@TableName("tm_message_detail")
public class MessageDetailDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "auto_id", type = IdType.AUTO)
    private Long autoId;

    /**
     * 主键
     */
    private String messageId;

    /**
     * 请求id
     */
    private String requestId;

    /**
     * 会员ID
     */
    private String memberId;

    /**
     * 业务id
     */
    private String batchId;

    /**
     * 场景编码
     */
    private String sceneCode;

    /**
     * 收件方，手机号、邮箱
     */
    private String recipient;

    /**
     * 协议，如短信、邮件、push
     */
    private String protocol;

    /**
     * 消息类型，如普通消息、验证码
     */
    private String messageType;

    /**
     * 通知类型，如实时、延时
     */
    private String notifyType;

    /**
     * 通知时间
     */
    private LocalDateTime notifyTime;

    /**
     * 状态,待发送、成功、失败
     */
    private String status;

    /**
     * 备注
     */
    private String memo;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 最后修改时间
     */
    private LocalDateTime gmtModified;

    public Long getAutoId() {
        return autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public LocalDateTime getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(LocalDateTime notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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
        return "MessageDetailDO{" +
        "autoId = " + autoId +
        ", messageId = " + messageId +
        ", requestId = " + requestId +
        ", memberId = " + memberId +
        ", batchId = " + batchId +
        ", sceneCode = " + sceneCode +
        ", recipient = " + recipient +
        ", protocol = " + protocol +
        ", messageType = " + messageType +
        ", notifyType = " + notifyType +
        ", notifyTime = " + notifyTime +
        ", status = " + status +
        ", memo = " + memo +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        "}";
    }
}
