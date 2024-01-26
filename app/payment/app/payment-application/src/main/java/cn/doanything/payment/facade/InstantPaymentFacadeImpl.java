package cn.doanything.payment.facade;

import cn.doanything.payment.application.instant.InstantPaymentBuilder;
import cn.doanything.payment.application.instant.InstantPaymentService;
import cn.doanything.payment.domain.instant.InstantPayment;
import cn.doanything.payment.facade.request.InstantPaymentRequest;
import cn.doanything.payment.facade.response.InstantPaymentResponse;
import cn.doanything.payment.types.PayResult;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wxj
 * 2024/1/15
 */
@DubboService
public class InstantPaymentFacadeImpl implements InstantPaymentFacade {

    @Autowired
    private InstantPaymentBuilder instantPaymentBuilder;

    @Autowired
    private InstantPaymentService instantPaymentService;

    @Override
    public InstantPaymentResponse pay(InstantPaymentRequest request) {
        InstantPayment instantPayment = instantPaymentBuilder.build(request);
        PayResult result = instantPaymentService.pay(instantPayment);
        InstantPaymentResponse response = new InstantPaymentResponse();
        response.setPaymentId(instantPayment.getPaymentId());
        response.setOrderId(instantPayment.getPayOrder().getOrderId());
        response.setOrderStatus(instantPayment.getPayOrder().getOrderStatus());
        return response;
    }
}
