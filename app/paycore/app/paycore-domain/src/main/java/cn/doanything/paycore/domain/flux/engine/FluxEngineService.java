package cn.doanything.paycore.domain.flux.engine;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.types.PayResult;

/**
 * @author wxj
 * 2024/1/27
 */
public interface FluxEngineService {

    PayResult process(FluxOrder fluxOrder);
}
