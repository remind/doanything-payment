package cn.doanything.account.infrastructure.persistence.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wxj
 * @since 2023-12-21
 */
@TableName("ta_outer_account")
public class OuterAccountDO implements Serializable {

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
     * 会员号
     */
    private String memberId;

    /**
     * 状态，共6位，每位表示一种类型的状态
     */
    private String statusMap;

    /**
     * 账户属性 1:对私，2:对公
     */
    private String accountAttribute;

    /**
     * 账户类型
     */
    private String accountType;

    /**
     * 当前余额方向 1:借，2:贷
     */
    private String currentBalanceDirection;

    /**
     * 账户余额方向 1:借，2:贷，0:双向
     */
    private String balanceDirection;

    /**
     * 货币类型
     */
    private String currencyCode;

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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(String statusMap) {
        this.statusMap = statusMap;
    }

    public String getAccountAttribute() {
        return accountAttribute;
    }

    public void setAccountAttribute(String accountAttribute) {
        this.accountAttribute = accountAttribute;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCurrentBalanceDirection() {
        return currentBalanceDirection;
    }

    public void setCurrentBalanceDirection(String currentBalanceDirection) {
        this.currentBalanceDirection = currentBalanceDirection;
    }

    public String getBalanceDirection() {
        return balanceDirection;
    }

    public void setBalanceDirection(String balanceDirection) {
        this.balanceDirection = balanceDirection;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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
        return "OuterAccountDO{" +
        "accountNo = " + accountNo +
        ", accountTitleNo = " + accountTitleNo +
        ", accountName = " + accountName +
        ", memberId = " + memberId +
        ", statusMap = " + statusMap +
        ", accountAttribute = " + accountAttribute +
        ", accountType = " + accountType +
        ", currentBalanceDirection = " + currentBalanceDirection +
        ", balanceDirection = " + balanceDirection +
        ", currencyCode = " + currencyCode +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        "}";
    }
}
