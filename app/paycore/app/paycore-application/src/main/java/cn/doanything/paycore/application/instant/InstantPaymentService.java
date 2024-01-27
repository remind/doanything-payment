package cn.doanything.paycore.application.instant;

import cn.doanything.paycore.application.AbstractPaymentService;
import cn.doanything.paycore.domain.instant.InstantPayment;
import cn.doanything.paycore.domain.payorder.PayOrder;
import cn.doanything.paycore.domain.repository.InstantPaymentRepository;
import cn.doanything.paycore.types.PayResult;
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
            return pay(payment, (PayOrder) payment.getBasePayOrder());
        });
    }


}
