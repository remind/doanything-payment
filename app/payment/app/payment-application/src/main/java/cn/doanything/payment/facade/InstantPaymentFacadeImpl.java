package cn.doanything.payment.facade;

import cn.doanything.payment.application.instant.InstantPaymentBuilder;
import cn.doanything.payment.domain.instant.InstantPayment;
import cn.doanything.payment.facade.request.InstantPaymentRequest;
import cn.doanything.payment.facade.response.InstantPaymentResponse;
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

    @Override
    public InstantPaymentResponse pay(InstantPaymentRequest request) {
        InstantPayment instantPayment = instantPaymentBuilder.build(request);
        return null;
    }
}
