package cn.doanything.account.infrastructure.persistence.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 外部户子账户
 * </p>
 *
 * @author wxj
 * @since 2023-12-19
 */
@TableName("ta_outer_sub_account")
public class OuterSubAccountDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账户号
     */
    private String accountNo;

    /**
     * 资金类型
     */
    private String fundType;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 可用余额
     */
    private BigDecimal availableBalance;

    /**
     * 币种代码
     */
    private String currencyCode;

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

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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
        return "OuterSubAccountDO{" +
        "accountNo = " + accountNo +
        ", fundType = " + fundType +
        ", balance = " + balance +
        ", availableBalance = " + availableBalance +
        ", currencyCode = " + currencyCode +
        ", memo = " + memo +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        "}";
    }
}
