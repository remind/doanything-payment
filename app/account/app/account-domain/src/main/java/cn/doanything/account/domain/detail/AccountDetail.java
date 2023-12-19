package cn.doanything.account.domain.detail;

import cn.doanything.account.types.enums.CrDr;
import cn.doanything.account.types.enums.IODirection;
import cn.doanything.commons.lang.types.Money;
import lombok.Data;

/**
 * @author wxj
 * 2023/12/16
 */
@Data
public abstract class AccountDetail extends BaseDetail {

    /**
     * 入账前余额
     */
    private Money beforeBalance = new Money();

    /**
     * 入账后余额
     */
    private Money afterBalance = new Money();

    /**
     * 借贷标志
     */
    private CrDr crdr;

    /**
     * 加减方向
     */
    private IODirection ioDirection;

}
