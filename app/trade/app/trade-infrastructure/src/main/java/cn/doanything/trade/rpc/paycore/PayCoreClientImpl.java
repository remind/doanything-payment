package cn.doanything.trade.rpc.paycore;

import cn.doanything.commons.payment.result.PayResult;
import cn.doanything.paycore.facade.InstantPaymentFacade;
import cn.doanything.paycore.facade.request.InstantPaymentRequest;
import cn.doanything.trade.domain.payment.PaymentOrder;
import cn.doanything.trade.domain.rpc.paycore.PayCoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2024/2/3
 */
@Service
public class PayCoreClientImpl implements PayCoreClient {

    @Autowired
    private InstantPaymentFacade instantPaymentFacade;
    @Override
    public PayResult pay(PaymentOrder paymentOrder) {
        InstantPaymentRequest paymentRequest = new InstantPaymentRequest();
        paymentRequest.setPayAmount(paymentOrder.getAmount());
        return null;
    }
}
