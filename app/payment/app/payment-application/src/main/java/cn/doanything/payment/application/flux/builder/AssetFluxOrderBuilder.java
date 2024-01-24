package cn.doanything.payment.application.flux.builder;

import cn.doanything.payment.domain.BasePayOrder;
import cn.doanything.payment.domain.flux.AssetFluxOrder;
import cn.doanything.payment.domain.flux.FluxOrderStatus;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2024/1/24
 */
@Service
public class AssetFluxOrderBuilder {

    public AssetFluxOrder build(BasePayOrder payOrder) {
        AssetFluxOrder assetFluxOrder = new AssetFluxOrder();
        assetFluxOrder.setPaymentId(payOrder.getPaymentId());
        assetFluxOrder.setOrderId(payOrder.getOrderId());
        assetFluxOrder.setStatus(FluxOrderStatus.ACCEPT);
        return assetFluxOrder;
    }
}
