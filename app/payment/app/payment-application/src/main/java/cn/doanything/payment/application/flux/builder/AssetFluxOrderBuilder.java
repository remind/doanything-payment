package cn.doanything.payment.application.flux.builder;

import cn.doanything.payment.domain.BasePayOrder;
import cn.doanything.payment.domain.flux.AssetFluxOrder;
import cn.doanything.payment.domain.flux.InstructStatus;
import cn.doanything.payment.domain.flux.service.FluxDomainService;
import cn.doanything.payment.domain.service.IdGeneratorDomainService;
import cn.doanything.payment.types.IdType;
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
