package cn.doanything.basic.infrastructure.persistence.mns.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 消息模板
 * </p>
 *
 * @author wxj
 * @since 2024-01-06
 */
@TableName("tm_message_template")
public class MessageTemplateDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 场景编码
     */
    private String sceneCode;

    /**
     * 协议，如短信、邮件、push
     */
    private String protocol;

    /**
     * 状态，启用、禁用
     */
    private String status;

    /**
     * 主题，邮箱时必须有
     */
    private String subject;

    /**
     * 模板内容
     */
    private String content;

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

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "MessageTemplateDO{" +
        "sceneCode = " + sceneCode +
        ", protocol = " + protocol +
        ", status = " + status +
        ", subject = " + subject +
        ", content = " + content +
        ", memo = " + memo +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        "}";
    }
}
