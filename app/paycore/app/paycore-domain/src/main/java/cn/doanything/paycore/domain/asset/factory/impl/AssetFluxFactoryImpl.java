package cn.doanything.paycore.domain.asset.factory.impl;

import cn.doanything.paycore.domain.asset.FluxInstructBuilder;
import cn.doanything.paycore.domain.asset.FluxInstructionExecutor;
import cn.doanything.paycore.domain.asset.factory.AssetFluxFactory;
import cn.doanything.paycore.types.asset.AssetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wxj
 * 2024/1/27
 */
@Service
public class AssetFluxFactoryImpl implements AssetFluxFactory {

    @Autowired
    private Map<String, FluxInstructBuilder> builderMap;

    @Autowired
    private Map<String, FluxInstructionExecutor> executorMap;


    @Override
    public FluxInstructBuilder getFluxInstructBuilder(AssetType assetType) {
        FluxInstructBuilder builder = builderMap.get(assetType.getCode() + "_FluxInstructBuilder");
        return builder != null ? builder : builderMap.get("CHANNEL_FluxInstructBuilder");
    }

    @Override
    public FluxInstructionExecutor getFluxInstructionExecutor(AssetType assetType) {
        FluxInstructionExecutor executor = executorMap.get(assetType.getCode() + "_FluxInstructExecutor");
        return executor != null ? executor : executorMap.get("CHANNEL_FluxInstructExecutor");
    }
}
