package cn.doanything.payment.domain;

import cn.doanything.commons.lang.Entity;
import cn.doanything.payment.types.PaymentType;
import lombok.Data;

/**
 * 支付总单
 *
 * @author wxj
 * 2024/1/15
 */
@Data
public class BasePayment extends Entity {

    /**
     * 支付总单号
     */
    private String paymentId;

    /**
     * 支付类型
     */
    private PaymentType paymentType;

    /**
     * 发起人用户ID
     */
    private String memberId;

    /**
     * 商户ID
     */
    private String merchantId;


}
