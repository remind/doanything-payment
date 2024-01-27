package cn.doanything.paycore.domain.asset.impl;

import cn.doanything.paycore.domain.asset.AssetFluxFactory;
import cn.doanything.paycore.domain.asset.FluxInstructBuilder;
import cn.doanything.paycore.domain.asset.FluxInstructionExecutor;
import cn.doanything.paycore.domain.asset.balance.BalanceFluxInstructBuilder;
import cn.doanything.paycore.domain.asset.channel.ChannelFluxInstructBuilder;
import cn.doanything.paycore.types.asset.AssetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2024/1/27
 */
@Service
public class AssetFluxFactoryImpl implements AssetFluxFactory {

    @Autowired
    @Qualifier("channelFluxInstructBuilder")
    private ChannelFluxInstructBuilder channelFluxInstructBuilder;

    @Autowired
    @Qualifier("balanceFluxInstructProcessor")
    private BalanceFluxInstructBuilder balanceFluxInstructBuilder;


    @Override
    public FluxInstructBuilder getFluxInstructBuilder(AssetType assetType) {
        switch (assetType) {
            case BALANCE:
                return balanceFluxInstructBuilder;
            default: return channelFluxInstructBuilder;
        }
    }

    @Override
    public FluxInstructionExecutor getFluxInstructionExecutor(AssetType assetType) {
        return null;
    }
}
