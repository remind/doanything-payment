package cn.doanything.paycore.application;

import cn.doanything.paycore.application.flux.FluxService;
import cn.doanything.paycore.application.flux.builder.AssetFluxOrderBuilder;
import cn.doanything.paycore.domain.payorder.BasePayOrder;
import cn.doanything.paycore.domain.BasePayment;
import cn.doanything.paycore.domain.payorder.PayOrder;
import cn.doanything.paycore.domain.payorder.service.PayOrderDomainService;
import cn.doanything.paycore.types.PayResult;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wxj
 * 2024/1/26
 */
public abstract class AbstractPaymentService {

    @Autowired
    private AssetFluxOrderBuilder fluxOrderBuilder;

    @Autowired
    private FluxService fluxService;

    @Autowired
    private PayOrderDomainService payOrderDomainService;

    @SuppressWarnings({"rawtypes"})
    protected PayResult pay(BasePayment payment, PayOrder payOrder) {
        payOrderDomainService.pay(payOrder);
        return null;
    }

    protected abstract void payCallBack(BasePayment payment, BasePayOrder basePayOrder, PayResult payResult);
}
