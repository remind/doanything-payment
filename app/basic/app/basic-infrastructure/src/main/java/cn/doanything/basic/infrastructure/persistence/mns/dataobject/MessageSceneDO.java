package cn.doanything.basic.infrastructure.persistence.mns.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 消息场景
 * </p>
 *
 * @author wxj
 * @since 2024-01-06
 */
@TableName("tm_message_scene")
public class MessageSceneDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    @TableId("code")
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 状态，启用、禁用
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "MessageSceneDO{" +
        "code = " + code +
        ", name = " + name +
        ", status = " + status +
        ", memo = " + memo +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        "}";
    }
}
