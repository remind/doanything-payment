package cn.doanything.paycore.domain.flux.engine;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;

/**
 * @author wxj
 * 2024/1/27
 */
public interface FluxEngineService {

    FluxInstruction process(FluxOrder fluxOrder);
}
