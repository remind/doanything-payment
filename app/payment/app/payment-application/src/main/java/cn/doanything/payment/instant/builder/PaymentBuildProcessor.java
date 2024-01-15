package cn.doanything.payment.instant.builder;

import cn.doanything.payment.facade.request.BasePaymentRequest;

/**
 * @author wxj
 * 2024/1/15
 */
public interface PaymentBuildProcessor {

    /**
     *
     * @param request   请求对象
     * @param domain    领域对象
     */
    void process(BasePaymentRequest request, Object domain);
}
