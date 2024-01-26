package cn.doanything.paycore.application.flux.instruct;

import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.domain.flux.FluxInstruction;

/**
 * @author wxj
 * 2024/1/26
 */
public interface FluxInstructExecutor {

    ExecutorResult execute(FluxOrder fluxOrder, FluxInstruction fluxInstruction);
}
