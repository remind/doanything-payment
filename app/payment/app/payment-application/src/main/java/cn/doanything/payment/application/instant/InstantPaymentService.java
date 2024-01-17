package cn.doanything.payment.application.instant;

import cn.doanything.payment.domain.instant.InstantPayment;
import cn.doanything.payment.domain.repository.InstantPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author wxj
 * 2024/1/17
 */
@Service
public class InstantPaymentService {

    @Autowired
    private InstantPaymentRepository instantPaymentRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public void pay(InstantPayment payment) {
        instantPaymentRepository.store(payment);
    }
}
