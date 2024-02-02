package cn.doanything.trade.domain.payment;

import lombok.Data;

/**
 * 支付单
 * @author wxj
 * 2024/2/2
 */
@Data
public class PaymentOrder {

    private String tradeId;

    private String paymentId;

    private String payOrderId;
}
