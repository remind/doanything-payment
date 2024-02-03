package cn.doanything.trade.facade.fund.deposit;

import cn.doanything.commons.payment.result.PayStatus;
import cn.doanything.trade.facade.TradeResponse;
import lombok.Data;

import java.util.Map;

/**
 * @author wxj
 * 2024/2/3
 */
@Data
public class DepositResponse extends TradeResponse {

    /**
     * 支付状态
     */
    private PayStatus status;

    /**
     * 支付参数
     */
    private Map<String, String> payParam;
}
