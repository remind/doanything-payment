package cn.doanything.paycore.infrastructure.persistence.repository;

import cn.doanything.paycore.domain.BasePayment;
import cn.doanything.paycore.infrastructure.persistence.convertor.PaymentDalConvertor;
import cn.doanything.paycore.infrastructure.persistence.dataobject.PaymentDO;
import cn.doanything.paycore.infrastructure.persistence.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wxj
 * 2024/1/18
 */
public abstract class AbstractPaymentRepository {

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private PaymentDalConvertor paymentDalConvertor;

    protected BasePayment loadPayment(String paymentId, boolean lock) {
        PaymentDO paymentDO;
        if (lock) {
            paymentDO = paymentMapper.lockById(paymentId);
        } else {
            paymentDO = paymentMapper.selectById(paymentId);
        }
        return paymentDalConvertor.toPayment(paymentDO);
    }

    protected boolean storePayment(BasePayment payment) {
        PaymentDO paymentDO = paymentDalConvertor.toDo(payment);
        return paymentMapper.insert(paymentDO) == 1;
    }

}
