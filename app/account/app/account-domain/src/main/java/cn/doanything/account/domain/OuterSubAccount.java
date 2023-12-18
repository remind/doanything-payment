package cn.doanything.account.domain;

import cn.doanything.commons.lang.Entity;
import cn.doanything.commons.lang.types.Money;
import lombok.Data;

import java.util.Date;

/**
 * @author wxj
 * 2023/12/16
 */
@Data
public class OuterSubAccount extends Entity {

    /**
     * 账户号
     **/
    private String accountNo;

    /**
     * 资金类型
     */
    private String fundType;

    /**
     * 余额
     **/
    private Money balance = new Money(0);

    /**
     * 可用余额
     */
    private Money availableBalance = new Money();
    /**
     * 备注
     **/
    private String memo;

}
