package cn.doanything.trade.application;

import cn.doanything.trade.domain.TradeOrder;
import cn.doanything.trade.domain.payment.PaymentOrder;
import cn.doanything.trade.domain.payment.PaymentStatus;
import cn.doanything.trade.domain.payment.service.PaymentDomainService;
import cn.doanything.trade.domain.service.IdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wxj
 * 2024/2/2
 */
public abstract class AbstractBaseTradeService {

    @Autowired
    private PaymentDomainService paymentDomainService;

    @Autowired
    private IdGeneratorService idGeneratorService;

    protected PaymentOrder initPaymentOrder(TradeOrder tradeOrder) {
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setTradeId(tradeOrder.getTradeId());
        paymentOrder.setAmount(tradeOrder.getAmount());
        paymentOrder.setStatus(PaymentStatus.INIT);
        paymentOrder.setPaymentOrderId(idGeneratorService.genPaymentOrderId(tradeOrder.getTradeId()));
        return paymentOrder;
    }

    protected void pay(PaymentOrder paymentOrder) {
        paymentDomainService.pay(paymentOrder);
    }
}
