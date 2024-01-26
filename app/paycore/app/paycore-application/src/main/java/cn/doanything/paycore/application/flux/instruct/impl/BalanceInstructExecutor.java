package cn.doanything.paycore.application.flux.instruct.impl;

import cn.doanything.paycore.application.flux.instruct.ExecutorResult;
import cn.doanything.paycore.application.flux.instruct.FluxInstructExecutor;
import cn.doanything.paycore.domain.flux.AssetFluxOrder;
import cn.doanything.paycore.domain.flux.BalanceAssetFluxInstruct;
import cn.doanything.paycore.domain.flux.AssetFluxInstruct;
import cn.doanything.paycore.domain.flux.InstructStatus;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * 2024/1/26
 */
@Component
public class BalanceInstructExecutor implements FluxInstructExecutor {
    @Override
    public ExecutorResult execute(AssetFluxOrder fluxOrder, AssetFluxInstruct assetFluxInstruct) {
        BalanceAssetFluxInstruct balanceFluxInstruct = (BalanceAssetFluxInstruct) assetFluxInstruct;
        ExecutorResult result = new ExecutorResult();
        result.setStatus(InstructStatus.SUCCESS);
        return result;
    }
}
