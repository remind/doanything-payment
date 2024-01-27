package cn.doanything.paycore.domain.flux.service;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;

/**
 * @author wxj
 * 2024/1/26
 */
public interface FluxOrderDomainService {

    void failHandle(FluxOrder fluxOrder, FluxInstruction failInstruct);
    void reverse(FluxOrder fluxOrder, FluxInstruction failInstruct);
}
