package cn.doanything.account.domain.detail;

import cn.doanything.commons.lang.Entity;
import cn.doanything.commons.lang.types.Money;
import lombok.Data;

/**
 * 基础明细
 * @author wxj
 * 2023/12/19
 */
@Data
public class BaseDetail extends Entity {
    /**
     * 明细流水号
     */
    private String voucherNo;

    /**
     * 请求号，请求号内借贷平衡
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
     * 会计日
     **/
    private String accountingDate;

    /**
     * 备注
     **/
    private String memo;
}
