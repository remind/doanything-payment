package cn.doanything.trade.facade.fund.withdrawal;

import cn.doanything.trade.facade.TradeResponse;
import cn.doanything.types.status.FundOrderStatus;
import lombok.Data;

/**
 * @author wxj
 * 2024/2/3
 */
@Data
public class WithdrawalResponse extends TradeResponse {

    /**
     * 提现状态
     */
    private FundOrderStatus status;
}
