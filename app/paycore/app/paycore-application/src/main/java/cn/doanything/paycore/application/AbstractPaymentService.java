package cn.doanything.paycore.application;

import cn.doanything.paycore.application.flux.FluxService;
import cn.doanything.paycore.application.flux.builder.AssetFluxOrderBuilder;
import cn.doanything.paycore.domain.BasePayOrder;
import cn.doanything.paycore.domain.BasePayment;
import cn.doanything.paycore.domain.flux.AssetFluxOrder;
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

    @SuppressWarnings({"rawtypes"})
    protected PayResult pay(BasePayment payment, BasePayOrder basePayOrder) {
        AssetFluxOrder fluxOrder = fluxOrderBuilder.build(basePayOrder);
        PayResult payResult = fluxService.process(fluxOrder);
        payCallBack(payment, basePayOrder, payResult);
        return payResult;
    }

    protected abstract void payCallBack(BasePayment payment, BasePayOrder basePayOrder, PayResult payResult);
}
