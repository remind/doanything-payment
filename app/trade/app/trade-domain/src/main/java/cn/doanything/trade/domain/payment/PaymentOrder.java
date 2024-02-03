package cn.doanything.trade.domain.payment;

import cn.doanything.commons.lang.Entity;
import cn.doanything.commons.lang.types.Money;
import lombok.Data;

import java.util.List;

/**
 * 支付单
 * @author wxj
 * 2024/2/2
 */
@Data
public class PaymentOrder extends Entity {

    /**
     * 交易号
     */
    private String tradeId;

    /**
     * 支付请求id
     */
    private String paymentOrderId;

    /**
     * 支付订单id
     */
    private String payOrderId;

    /**
     * 支付金额
     */
    private Money amount;

    /**
     * 支付状态
     */
    private PaymentStatus status;

    /**
     * 支付人
     */
    private String payeeId;

    /**
     * 付款方
     */
    private List<PaymentParty> payerParty;

    /**
     * 收款方
     */
    private List<PaymentParty> payeeParty;

}
