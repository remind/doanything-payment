package cn.doanything.account.infrastructure.persistence.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 缓冲规则
 * </p>
 *
 * @author wxj
 * @since 2023-12-25
 */
@TableName("t_buffered_rule")
public class BufferedRuleDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rule_id", type = IdType.AUTO)
    private Long ruleId;

    /**
     * 账户号
     */
    private String accountNo;

    /**
     * 借贷标志，为空则均支持
     */
    private String crDr;

    /**
     * 状态
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

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCrDr() {
        return crDr;
    }

    public void setCrDr(String crDr) {
        this.crDr = crDr;
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
        return "BufferedRuleDO{" +
        "ruleId = " + ruleId +
        ", accountNo = " + accountNo +
        ", crDr = " + crDr +
        ", status = " + status +
        ", memo = " + memo +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        "}";
    }
}
