package cn.doanything.trade.domain.repository;

import cn.doanything.trade.domain.payment.PaymentOrder;

/**
 * @author wxj
 * 2024/2/18
 */
public interface PaymentOrderRepository {

    void store(PaymentOrder paymentOrder);
}
