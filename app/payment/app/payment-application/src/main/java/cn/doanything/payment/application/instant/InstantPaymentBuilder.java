package cn.doanything.payment.application.instant;

import cn.doanything.payment.application.builder.PaymentBuilder;
import cn.doanything.payment.types.IdType;
import cn.doanything.payment.domain.instant.InstantPayment;
import cn.doanything.payment.domain.instant.PayOrder;
import cn.doanything.payment.domain.instant.PayOrderStatus;
import cn.doanything.payment.facade.request.InstantPaymentRequest;
import cn.doanything.payment.types.PaymentType;
import cn.doanything.payment.types.asset.BelongTo;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * 2024/1/15
 */
@Component
public class InstantPaymentBuilder extends PaymentBuilder {

    public InstantPayment build(InstantPaymentRequest request) {
        InstantPayment instantPayment = new InstantPayment();
        fillPayment(instantPayment, request, PaymentType.INSTANT);
        instantPayment.setPayOrder(buildPayOrder(instantPayment.getPaymentId(), request));
        return instantPayment;
    }

    private PayOrder buildPayOrder(String paymentId, InstantPaymentRequest request) {
        PayOrder payOrder = new PayOrder();
        payOrder.setOrderId(idGeneratorService.genSubPayOrder(paymentId, IdType.PAY_ORDER_ID));
        payOrder.setRequestId(request.getRequestId());
        payOrder.setOrderAmount(request.getAmount());
        payOrder.setMemberId(request.getMemberId());
        payOrder.setOrderStatus(PayOrderStatus.INIT);
        payOrder.setPayeeDetails(buildFundDetails(request.getMemberId(), payOrder.getOrderId(), request.getPayeeFundDetail(), BelongTo.PAYEE));
        payOrder.setPayerDetails(buildFundDetails(request.getMemberId(), payOrder.getOrderId(), request.getPayerFundDetail(), BelongTo.PAYER));
        return payOrder;
    }
}
