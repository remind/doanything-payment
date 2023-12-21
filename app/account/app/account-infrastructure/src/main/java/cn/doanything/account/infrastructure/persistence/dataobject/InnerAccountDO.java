package cn.doanything.account.infrastructure.persistence.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wxj
 * @since 2023-12-21
 */
@TableName("ta_inner_account")
public class InnerAccountDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账户号
     */
    private String accountNo;

    /**
     * 科目号
     */
    private String accountTitleNo;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 当前余额方向 1:借，2:贷
     */
    private Short currBalDirection;

    /**
     * 账户余额方向 1:借，2:贷，0:双向
     */
    private Short balDirection;

    /**
     * 货币类型
     */
    private String currencyCode;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 最后修改时间
     */
    private LocalDateTime gmtModified;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountTitleNo() {
        return accountTitleNo;
    }

    public void setAccountTitleNo(String accountTitleNo) {
        this.accountTitleNo = accountTitleNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Short getCurrBalDirection() {
        return currBalDirection;
    }

    public void setCurrBalDirection(Short currBalDirection) {
        this.currBalDirection = currBalDirection;
    }

    public Short getBalDirection() {
        return balDirection;
    }

    public void setBalDirection(Short balDirection) {
        this.balDirection = balDirection;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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
        return "InnerAccountDO{" +
        "accountNo = " + accountNo +
        ", accountTitleNo = " + accountTitleNo +
        ", accountName = " + accountName +
        ", currBalDirection = " + currBalDirection +
        ", balDirection = " + balDirection +
        ", currencyCode = " + currencyCode +
        ", balance = " + balance +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        "}";
    }
}
