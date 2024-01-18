package cn.doanything.payment.domain.repository;

import cn.doanything.payment.domain.instant.InstantPayment;

/**
 * @author wxj
 * 2024/1/17
 */
public interface InstantPaymentRepository {

    void store(InstantPayment payment);

    void load(String paymentId);
}
