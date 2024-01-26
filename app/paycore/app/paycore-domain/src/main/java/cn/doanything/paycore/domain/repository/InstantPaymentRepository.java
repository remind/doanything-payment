package cn.doanything.paycore.domain.repository;

import cn.doanything.paycore.domain.instant.InstantPayment;

/**
 * @author wxj
 * 2024/1/17
 */
public interface InstantPaymentRepository {

    void store(InstantPayment payment);

    void load(String paymentId);
}
