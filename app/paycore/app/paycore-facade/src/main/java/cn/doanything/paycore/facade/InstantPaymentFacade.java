package cn.doanything.paycore.facade;

import cn.doanything.paycore.facade.request.InstantPaymentRequest;
import cn.doanything.paycore.facade.response.InstantPaymentResponse;

/**
 * 直接支付
 * @author remind
 * 2023年07月14日 20:27
 */
public interface InstantPaymentFacade {

    /**
     * 支付
     * @param request
     * @return
     */
    InstantPaymentResponse pay(InstantPaymentRequest request);
}
