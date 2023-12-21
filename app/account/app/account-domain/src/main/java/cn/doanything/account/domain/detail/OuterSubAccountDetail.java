package cn.doanything.account.domain.detail;

import cn.doanything.account.types.enums.CrDr;
import cn.doanything.account.types.enums.IODirection;
import cn.doanything.commons.lang.Entity;
import cn.doanything.commons.lang.types.Money;
import lombok.Data;

/**
 * @author wxj
 * 2023/12/17
 */
@Data
public class OuterSubAccountDetail extends Entity {

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
    private CrDr crdr;

    /**
     * 会计日
     **/
    private String accountingDate;

    /**
     * 资金类型
     */
    private String fundType;

    /**
     * 入账前余额
     */
    private Money beforeBalance = new Money();

    /**
     * 入账后余额
     */
    private Money afterBalance = new Money();

    /**
     * 加减方向
     */
    private IODirection ioDirection;

    /**
     * 备注
     **/
    private String memo;
}
