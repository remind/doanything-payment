package cn.doanything.paycore.domain.flux.service;

import cn.doanything.paycore.domain.flux.balance.BalanceFluxInstructProcessor;
import cn.doanything.paycore.domain.flux.external.ExternalFluxInstructProcessor;
import cn.doanything.paycore.domain.flux.processor.FluxInstructProcessor;
import cn.doanything.paycore.types.asset.AssetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author wxj
 * 2024/1/26
 */
public abstract class AbstractFluxService {

    @Autowired
    @Qualifier("externalFluxInstructProcessor")
    private ExternalFluxInstructProcessor externalFluxInstructProcessor;

    @Autowired
    @Qualifier("balanceFluxInstructProcessor")
    private BalanceFluxInstructProcessor balanceFluxInstructProcessor;

    public FluxInstructProcessor getProcessor(AssetType assetType) {
        switch (assetType) {
            case BALANCE:
                return balanceFluxInstructProcessor;
            default: return externalFluxInstructProcessor;
        }
    }
}
