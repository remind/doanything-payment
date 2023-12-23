package cn.doanything.account.infrastructure.persistence.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 入账事务表
 * </p>
 *
 * @author wxj
 * @since 2023-12-23
 */
@TableName("t_account_transaction")
public class AccountTransactionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求号
     */
    private String requestNo;

    /**
     * 会计日
     */
    private String accountingDate;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 最后修改时间
     */
    private LocalDateTime gmtModified;

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(String accountingDate) {
        this.accountingDate = accountingDate;
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
        return "AccountTransactionDO{" +
        "requestNo = " + requestNo +
        ", accountingDate = " + accountingDate +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        "}";
    }
}
