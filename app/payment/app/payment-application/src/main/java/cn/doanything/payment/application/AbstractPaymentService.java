package cn.doanything.payment.application;

import cn.doanything.payment.application.flux.FluxService;
import cn.doanything.payment.application.flux.builder.AssetFluxOrderBuilder;
import cn.doanything.payment.domain.BasePayOrder;
import cn.doanything.payment.domain.BasePayment;
import cn.doanything.payment.domain.flux.AssetFluxOrder;
import cn.doanything.payment.types.PayResult;
import cn.doanything.payment.types.PayStatus;
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
