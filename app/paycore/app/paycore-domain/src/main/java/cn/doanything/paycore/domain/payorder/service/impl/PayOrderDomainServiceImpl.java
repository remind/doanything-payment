package cn.doanything.paycore.domain.payorder.service.impl;

import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.domain.flux.engine.FluxEngineService;
import cn.doanything.paycore.domain.payorder.PayOrder;
import cn.doanything.paycore.domain.payorder.PayOrderStatus;
import cn.doanything.paycore.domain.payorder.service.AbstractBasePayService;
import cn.doanything.paycore.domain.payorder.service.PayOrderDomainService;
import cn.doanything.paycore.domain.repository.BasePayOrderRepository;
import cn.doanything.paycore.domain.repository.FluxOrderRepository;
import cn.doanything.paycore.types.PayResult;
import cn.doanything.paycore.types.PayStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2024/1/27
 */
@Service
public class PayOrderDomainServiceImpl extends AbstractBasePayService implements PayOrderDomainService {

    @Autowired
    private FluxEngineService fluxEngineService;

    @Autowired
    private FluxOrderRepository fluxOrderRepository;

    @Autowired
    private BasePayOrderRepository basePayOrderRepository;
    @Override
    public PayResult pay(PayOrder payOrder) {
        FluxOrder fluxOrder = buildFluxOrder(payOrder);
        fluxOrderRepository.store(fluxOrder);
        PayResult payResult = fluxEngineService.process(fluxOrder);
        if (payResult.getPayStatus() == PayStatus.SUCCESS) {
            payOrder.setOrderStatus(PayOrderStatus.SUCCESS);
        }
        switch (payResult.getPayStatus()) {
            case SUCCESS:
                payOrder.setOrderStatus(PayOrderStatus.SUCCESS);
                break;
            case FAIL:
                payOrder.setOrderStatus(PayOrderStatus.FAIL);
                break;
            case PROCESS:
                payOrder.setOrderStatus(PayOrderStatus.PAYING);
                break;
        }
        basePayOrderRepository.reStore(payOrder);
        return payResult;
    }
}
