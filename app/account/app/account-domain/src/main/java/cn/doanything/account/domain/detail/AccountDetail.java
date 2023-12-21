package cn.doanything.account.domain.detail;

import cn.doanything.account.types.enums.CrDr;
import cn.doanything.commons.lang.Entity;
import cn.doanything.commons.lang.types.Money;
import lombok.Data;

/**
 * @author wxj
 * 2023/12/16
 */
@Data
public abstract class AccountDetail extends Entity {
    /**
     * 明细流水号
     */
    private String voucherNo;

    /**
     * 请求号
     */
    private String requestNo;

    /**
     * 帐号
     */
    private String accountNo;

    /**
     * 发生金额
     */
    private Money amount = new Money();

    /**
     * 借贷标志
     */
    private CrDr crDr;

    /**
     * 会计日
     **/
    private String accountingDate;

    /**
     * 备注
     **/
    private String memo;
}
