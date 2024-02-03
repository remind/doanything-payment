package cn.doanything.trade.domain.payment.service;

import cn.doanything.trade.domain.payment.PaymentOrder;

/**
 * @author wxj
 * 2024/2/2
 */
public interface PaymentDomainService {

    void pay(PaymentOrder paymentOrder);
}
