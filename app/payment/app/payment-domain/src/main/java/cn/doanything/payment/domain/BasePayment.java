package cn.doanything.payment.domain;

import cn.doanything.payment.types.PaymentType;
import lombok.Data;

/**
 * 支付总单
 * @author wxj
 * 2024/1/15
 */
@Data
public abstract class BasePayment {

    /**
     * 支付总单号
     */
    private String paymentId;

    /**
     * 支付类型
     */
    private PaymentType paymentType;

    /**
     * 付款方用户ID
     */
    private String payerId;

    /**
     * 商户ID
     */
    private String merchantId;
}
