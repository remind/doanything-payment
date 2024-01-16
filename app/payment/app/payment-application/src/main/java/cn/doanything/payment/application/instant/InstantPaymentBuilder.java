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
        fillBasePayment(instantPayment, request, PaymentType.INSTANT);
        instantPayment.setPayOrder(buildPayOrder(instantPayment.getPaymentId(), request));
        fillFundDetails(instantPayment.getPayOrder(), request);
        return instantPayment;
    }

    private PayOrder buildPayOrder(String paymentId, InstantPaymentRequest request) {
        PayOrder payOrder = new PayOrder();
        payOrder.setOrderId(idGeneratorDomainService.genSubPayOrder(paymentId, IdType.PAY_ORDER_ID));
        payOrder.setRequestId(request.getRequestId());
        payOrder.setOrderAmount(request.getPayAmount());
        payOrder.setMemberId(request.getPayerId());
        payOrder.setOrderStatus(PayOrderStatus.INIT);
        return payOrder;
    }

    /**
     * 填充资金明细
     * @param payOrder
     * @param request
     */
    private void fillFundDetails(PayOrder payOrder, InstantPaymentRequest request) {
        request.getPayerFundDetail().forEach(fundDetailInfo -> {
            payOrder.addPayerFundDetail(buildFundDetail(payOrder.getPaymentId(), payOrder.getOrderId(), fundDetailInfo, BelongTo.PAYER));
        });

        request.getTradeInfos().forEach(tradeInfo -> {
            tradeInfo.getPayeeFundDetail().forEach(fundDetailInfo -> {
                payOrder.addPayeeFundDetail(buildFundDetail(payOrder.getPaymentId(), payOrder.getOrderId(), fundDetailInfo, BelongTo.PAYEE));
            });
        });
    }

    private void fillFundRelations(PayOrder payOrder, InstantPaymentRequest request) {

    }
}
