package cn.doanything.paycore.domain.flux.engine.processor;

import cn.doanything.paycore.domain.asset.FluxResult;
import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;

/**
 * @author wxj
 * 2024/1/29
 */
public interface InstructResultProcessor {

    boolean process(FluxOrder fluxOrder, FluxInstruction fluxInstruction, FluxResult fluxResult);
}
