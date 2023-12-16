package cn.doanything.account.domain;

import cn.doanything.account.types.enums.AccountFamily;
import cn.doanything.account.types.enums.BalanceDirection;
import cn.doanything.account.types.enums.CrDr;
import cn.doanything.commons.lang.Entity;
import cn.doanything.commons.lang.types.Money;
import lombok.Data;

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
    protected String accountTitleNo;
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
     * 账户余额
     */
    protected Money balance = new Money(0);

    /**
     * 获取帐户分类
     *
     * @return
     */
    public abstract AccountFamily getAccountFamily();

}
