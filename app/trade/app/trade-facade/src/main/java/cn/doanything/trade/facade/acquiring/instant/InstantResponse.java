package cn.doanything.trade.facade.acquiring.instant;

import cn.doanything.trade.facade.TradeResponse;
import cn.doanything.types.status.AcquiringTradeStatus;
import lombok.Data;

/**
 * @author wxj
 * 2024/2/7
 */
@Data
public class InstantResponse extends TradeResponse {

    /**
     * 外部业务号
     */
    private String outTradeNo;

    /**
     * 交易状态
     */
    private AcquiringTradeStatus status;

}
