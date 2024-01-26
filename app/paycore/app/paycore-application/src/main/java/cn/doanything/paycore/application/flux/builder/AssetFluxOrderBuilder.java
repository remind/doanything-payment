package cn.doanything.paycore.application.flux.builder;

import cn.doanything.paycore.domain.BasePayOrder;
import cn.doanything.paycore.domain.flux.AssetFluxOrder;
import cn.doanything.paycore.domain.flux.service.FluxDomainService;
import cn.doanything.paycore.domain.service.IdGeneratorDomainService;
import cn.doanything.paycore.types.IdType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2024/1/24
 */
@Service
public class AssetFluxOrderBuilder {

    @Autowired
    private FluxDomainService fluxDomainService;

    @Autowired
    private IdGeneratorDomainService idGeneratorDomainService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public AssetFluxOrder build(BasePayOrder payOrder) {
        AssetFluxOrder assetFluxOrder = new AssetFluxOrder();
        assetFluxOrder.setPaymentId(payOrder.getPaymentId());
        assetFluxOrder.setOrderId(payOrder.getOrderId());
        assetFluxOrder.setFluxOrderId(idGeneratorDomainService.genIdByRelateId(payOrder.getPaymentId(), IdType.FLUX_ORDER_ID));
        fluxDomainService.pay(assetFluxOrder, payOrder.getPayerDetails(), payOrder.getPayeeDetails());
        return assetFluxOrder;
    }
}
