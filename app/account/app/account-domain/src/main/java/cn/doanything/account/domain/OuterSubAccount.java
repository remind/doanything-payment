package cn.doanything.account.domain;

import cn.doanything.commons.lang.Entity;
import cn.doanything.commons.lang.types.Money;

import java.util.Date;

/**
 * @author wxj
 * 2023/12/16
 */
public class OuterSubAccount extends Entity {

    /**
     * 账户号
     **/
    private String accountNo;

    /**
     * 资金属性
     */
    private String fundAttribute;
    /**
     * 余额
     **/
    private Money balance = new Money(0);
    /**
     * 备注
     **/
    private String remark;

}
