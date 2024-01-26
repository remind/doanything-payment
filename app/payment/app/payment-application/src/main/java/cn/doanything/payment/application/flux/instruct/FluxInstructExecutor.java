package cn.doanything.payment.application.flux.instruct;

import cn.doanything.payment.domain.flux.AssetFluxOrder;
import cn.doanything.payment.domain.flux.AssetFluxInstruct;

/**
 * @author wxj
 * 2024/1/26
 */
public interface FluxInstructExecutor {

    ExecutorResult execute(AssetFluxOrder fluxOrder, AssetFluxInstruct assetFluxInstruct);
}
