package cn.doanything.paycore.domain.flux.service;

import cn.doanything.paycore.domain.flux.FluxInstruction;

/**
 * @author wxj
 * 2024/1/26
 */
public interface FluxInstructDomainService {

    FluxInstruction createReverseInstruct(FluxInstruction fluxInstruction);
}
