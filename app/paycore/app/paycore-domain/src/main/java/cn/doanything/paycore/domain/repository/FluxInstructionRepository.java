package cn.doanything.paycore.domain.repository;

import cn.doanything.paycore.domain.flux.FluxInstruction;

import java.util.List;

/**
 * @author wxj
 * 2024/1/27
 */
public interface FluxInstructionRepository {

    void store(FluxInstruction fluxInstruction);

    void reStore(FluxInstruction fluxInstruction);

    void remove(List<String> fluxInstructionIds);
}
