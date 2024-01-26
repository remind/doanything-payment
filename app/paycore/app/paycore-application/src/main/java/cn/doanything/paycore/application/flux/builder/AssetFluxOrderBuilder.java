package cn.doanything.paycore.application.flux.builder;

import cn.doanything.paycore.domain.BasePayOrder;
import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.domain.flux.service.FluxOrderDomainService;
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
    private FluxOrderDomainService fluxOrderDomainService;

    @Autowired
    private IdGeneratorDomainService idGeneratorDomainService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public FluxOrder build(BasePayOrder payOrder) {
        FluxOrder fluxOrder = new FluxOrder();
        fluxOrder.setPaymentId(payOrder.getPaymentId());
        fluxOrder.setOrderId(payOrder.getOrderId());
        fluxOrder.setFluxOrderId(idGeneratorDomainService.genIdByRelateId(payOrder.getPaymentId(), IdType.FLUX_ORDER_ID));
        fluxOrderDomainService.process(fluxOrder, payOrder.getPayerDetails(), payOrder.getPayeeDetails());
        return fluxOrder;
    }
}
