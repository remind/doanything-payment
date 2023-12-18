package cn.doanything.account.domain.detail;

import cn.doanything.account.types.enums.CrDr;
import cn.doanything.account.types.enums.IODirection;
import cn.doanything.commons.lang.Entity;
import cn.doanything.commons.lang.types.Money;
import lombok.Data;

/**
 * @author wxj
 * 2023/12/16
 */
@Data
public abstract class AbstractDetail extends Entity {

    protected String voucherNo;
    /**
     * 系统跟踪号（支付流水号）
     */
    protected String systemTraceNo;
    /**
     * 套号
     */
    protected String suiteNo;
    /**
     * 帐号
     */
    protected String accountNo;
    /**
     * 金额
     */
    protected Money amount = new Money();
    /**
     * 交易后余额
     */
    protected Money afterAmount = new Money();
    /**
     * 借贷标志
     */
    protected CrDr crdr;
    /**
     * 加减方向
     */
    protected IODirection ioDirection;
    /**
     * 会计日
     **/
    private String accountingDate;
    /**
     * 备注
     **/
    protected String remark;
}
