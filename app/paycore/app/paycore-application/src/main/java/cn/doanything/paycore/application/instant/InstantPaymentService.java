package cn.doanything.paycore.application.instant;

import cn.doanything.paycore.application.AbstractPaymentService;
import cn.doanything.paycore.domain.BasePayOrder;
import cn.doanything.paycore.domain.BasePayment;
import cn.doanything.paycore.domain.instant.InstantPayment;
import cn.doanything.paycore.domain.instant.PayOrder;
import cn.doanything.paycore.domain.instant.PayOrderStatus;
import cn.doanything.paycore.domain.repository.InstantPaymentRepository;
import cn.doanything.paycore.types.PayResult;
import cn.doanything.paycore.types.PayStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author wxj
 * 2024/1/17
 */
@Service
public class InstantPaymentService extends AbstractPaymentService {

    @Autowired
    private InstantPaymentRepository instantPaymentRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public PayResult pay(InstantPayment payment) {
        return transactionTemplate.execute(status -> {
            instantPaymentRepository.store(payment);
            return pay(payment, payment.getBasePayOrder());
        });
    }

    @Override
    protected void payCallBack(BasePayment payment, BasePayOrder basePayOrder, PayResult payResult) {
        PayOrder payOrder = (PayOrder) basePayOrder;
        if (payResult.getPayStatus() == PayStatus.SUCCESS) {
            payOrder.setOrderStatus(PayOrderStatus.SUCCESS);
        } else if (payResult.getPayStatus() == PayStatus.FAIL) {
            payOrder.setOrderStatus(PayOrderStatus.FAIL);
        } else {
            payOrder.setOrderStatus(PayOrderStatus.PAYING);
        }
    }
}
