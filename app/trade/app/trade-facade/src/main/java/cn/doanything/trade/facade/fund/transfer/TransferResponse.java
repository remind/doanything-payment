package cn.doanything.trade.facade.fund.transfer;

import cn.doanything.commons.payment.result.PayStatus;
import cn.doanything.trade.facade.TradeResponse;
import lombok.Data;

/**
 * @author wxj
 * 2024/2/2
 */
@Data
public class TransferResponse extends TradeResponse {
    /**
     * 状态
     */
    private PayStatus status;

}
