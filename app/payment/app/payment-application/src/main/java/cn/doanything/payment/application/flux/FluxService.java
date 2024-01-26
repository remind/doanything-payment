package cn.doanything.payment.application.flux;

import cn.doanything.payment.domain.flux.AssetFluxOrder;
import cn.doanything.payment.types.PayResult;

/**
 * @author wxj
 * 2024/1/26
 */
public interface FluxService {

    PayResult process(AssetFluxOrder fluxOrder);
}
