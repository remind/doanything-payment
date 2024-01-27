package cn.doanything.paycore.domain.asset;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;

/**
 * @author wxj
 * 2024/1/26
 */
public interface FluxInstructionExecutor {

    FluxResult execute(FluxOrder fluxOrder, FluxInstruction fluxInstruction);
}
