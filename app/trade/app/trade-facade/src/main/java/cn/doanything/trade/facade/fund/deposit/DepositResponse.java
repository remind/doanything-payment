package cn.doanything.trade.facade.fund.deposit;

import cn.doanything.commons.payment.result.PayStatus;
import cn.doanything.trade.facade.TradeResponse;

import java.util.Map;

/**
 * @author wxj
 * 2024/2/3
 */
public class DepositResponse extends TradeResponse {

    /**
     * 支付状态
     */
    private PayStatus payStatus;

    /**
     * 支付参数
     */
    private Map<String, String> payParam;
}
