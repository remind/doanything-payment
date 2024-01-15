package cn.doanything.payment.facade.request;

import lombok.Data;

/**
 * @author remind
 * 2023年07月15日 11:46
 */
@Data
public abstract class BasePaymentRequest {

    /**
     * 支付请求号
     */
    private String requestId;

    /**
     * 发起人ID
     */
    private String memberId;

}
