package cn.doanything.paycore.application.flux;

import cn.doanything.paycore.domain.flux.AssetFluxOrder;
import cn.doanything.paycore.types.PayResult;

/**
 * @author wxj
 * 2024/1/26
 */
public interface FluxService {

    PayResult process(AssetFluxOrder fluxOrder);
}
