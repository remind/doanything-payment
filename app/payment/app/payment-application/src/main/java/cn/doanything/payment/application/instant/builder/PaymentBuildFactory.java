package cn.doanything.payment.application.instant.builder;

import cn.doanything.payment.domain.instant.PayOrder;
import cn.doanything.payment.facade.request.InstantPaymentRequest;

/**
 * @author wxj
 * 2024/1/15
 */
public interface PaymentBuildFactory {

    PayOrder build(InstantPaymentRequest request);
}
