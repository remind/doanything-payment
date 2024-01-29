package cn.doanything.paycore.domain.asset.factory;

import cn.doanything.paycore.domain.asset.FluxInstructBuilder;
import cn.doanything.paycore.domain.asset.FluxInstructionExecutor;
import cn.doanything.paycore.types.asset.AssetType;

/**
 * @author wxj
 * 2024/1/27
 */
public interface AssetFluxFactory {

    FluxInstructBuilder getFluxInstructBuilder(AssetType assetType);

    FluxInstructionExecutor getFluxInstructionExecutor(AssetType assetType);
}
