package cn.doanything.paycore.application.flux.instruct;

import cn.doanything.paycore.domain.flux.AssetFluxOrder;
import cn.doanything.paycore.domain.flux.AssetFluxInstruct;

/**
 * @author wxj
 * 2024/1/26
 */
public interface FluxInstructExecutor {

    ExecutorResult execute(AssetFluxOrder fluxOrder, AssetFluxInstruct assetFluxInstruct);
}
