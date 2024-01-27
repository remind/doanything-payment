package cn.doanything.paycore.domain.asset;

import cn.doanything.paycore.types.asset.AssetType;

/**
 * @author wxj
 * 2024/1/27
 */
public interface AssetFluxFactory {

    FluxInstructBuilder getFluxInstructBuilder(AssetType assetType);

    FluxInstructionExecutor getFluxInstructionExecutor(AssetType assetType);
}
