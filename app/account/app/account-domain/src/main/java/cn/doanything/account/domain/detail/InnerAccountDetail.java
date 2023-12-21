package cn.doanything.account.domain.detail;

import cn.doanything.account.types.enums.IODirection;
import cn.doanything.commons.lang.types.Money;
import lombok.Data;

/**
 * @author wxj
 * 2023/12/19
 */
@Data
public class InnerAccountDetail extends AccountDetail {

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
}
