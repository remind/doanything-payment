package cn.doanything.account.domain;

import cn.doanything.account.types.enums.AccountFamily;
import cn.doanything.account.types.enums.BalanceDirection;
import cn.doanything.account.types.enums.CrDr;
import cn.doanything.commons.lang.Entity;
import cn.doanything.commons.lang.types.Money;
import lombok.Data;

import java.util.Currency;

/**
 * 账户基础模型
 *
 * @author wxj
 * 2023/12/16
 */
@Data
public abstract class Account extends Entity {

    /**
     * 账户编号
     */
    protected String accountNo;
    /**
     * 科目号
     */
    protected String titleCode;
    /**
     * 账户名称
     */
    protected String accountName;
    /**
     * 当前余额方向
     */
    protected CrDr currentBalanceDirection;
    /**
     * 账户余额方向
     */
    protected BalanceDirection balanceDirection;
    /**
     * 币种代码
     */
    protected String currencyCode;
    /**
     * 获取帐户分类
     *
     * @return
     */
    public abstract AccountFamily getAccountFamily();

    /**
     * 获取余额
     * @return
     */
    public abstract Money getBalance();

}
