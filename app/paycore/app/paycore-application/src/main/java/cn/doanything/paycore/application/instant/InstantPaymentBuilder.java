package cn.doanything.paycore.application.instant;

import cn.doanything.paycore.application.builder.PaymentBuilder;
import cn.doanything.paycore.domain.instant.InstantPayment;
import cn.doanything.paycore.domain.payorder.PayOrder;
import cn.doanything.paycore.domain.payorder.PayOrderStatus;
import cn.doanything.paycore.facade.request.InstantPaymentRequest;
import cn.doanything.paycore.types.PayOrderType;
import cn.doanything.paycore.types.PaymentType;
import cn.doanything.paycore.types.asset.BelongTo;
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
        payOrder.setPaymentId(paymentId);
        payOrder.setOrderId(idGeneratorService.genIdByRelateId(paymentId, PayOrderType.PAY.getIdType()));
        payOrder.setRequestId(request.getRequestId());
        payOrder.setAmount(request.getPayAmount());
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

}
