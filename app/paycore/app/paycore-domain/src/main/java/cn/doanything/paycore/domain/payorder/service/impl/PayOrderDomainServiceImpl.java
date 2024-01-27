package cn.doanything.paycore.domain.payorder.service.impl;

import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.domain.flux.engine.FluxEngineService;
import cn.doanything.paycore.domain.payorder.PayOrder;
import cn.doanything.paycore.domain.payorder.service.AbstractBasePayService;
import cn.doanything.paycore.domain.payorder.service.PayOrderDomainService;
import cn.doanything.paycore.domain.repository.FluxOrderRepository;
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
    @Override
    public void pay(PayOrder payOrder) {
        FluxOrder fluxOrder = buildFluxOrder(payOrder);
        fluxOrderRepository.store(fluxOrder);
        fluxEngineService.process(fluxOrder);
    }
}
