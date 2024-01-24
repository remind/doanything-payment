package cn.doanything.payment.application.flux;

import cn.doanything.payment.domain.flux.AssetFluxInstruct;
import cn.doanything.payment.domain.flux.AssetFluxOrder;

/**
 * @author wxj
 * 2024/1/21
 */
public interface FluxExecutor {

    ExecutorResult execute(AssetFluxOrder fluxOrder, AssetFluxInstruct fluxInstruct);

}
