package cn.doanything.member.infrastructure.persistence.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 个人会员密码
 * </p>
 *
 * @author wxj
 * @since 2024-01-01
 */
@TableName("t_personal_password")
public class PersonalPasswordDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会员id
     */
    private String memberId;

    /**
     * 密码使用分类，1登录密码，2支付密码
     */
    private String useType;

    /**
     * 密码类型，如文本、手势、指纹
     */
    private String type;

    /**
     * 状态，1正常，2锁定
     */
    private String status;

    /**
     * 锁定结束时间
     */
    private LocalDateTime lockEndTime;

    /**
     * 错误次数
     */
    private Byte errorCount;

    /**
     * 上次错误日期
     */
    private LocalDateTime lastErrorDate;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLockEndTime() {
        return lockEndTime;
    }

    public void setLockEndTime(LocalDateTime lockEndTime) {
        this.lockEndTime = lockEndTime;
    }

    public Byte getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Byte errorCount) {
        this.errorCount = errorCount;
    }

    public LocalDateTime getLastErrorDate() {
        return lastErrorDate;
    }

    public void setLastErrorDate(LocalDateTime lastErrorDate) {
        this.lastErrorDate = lastErrorDate;
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
        return "PersonalPasswordDO{" +
        "id = " + id +
        ", memberId = " + memberId +
        ", useType = " + useType +
        ", type = " + type +
        ", status = " + status +
        ", lockEndTime = " + lockEndTime +
        ", errorCount = " + errorCount +
        ", lastErrorDate = " + lastErrorDate +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        "}";
    }
}
