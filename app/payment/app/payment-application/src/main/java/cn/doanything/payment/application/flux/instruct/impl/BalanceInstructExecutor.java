package cn.doanything.payment.application.flux.instruct.impl;

import cn.doanything.payment.application.flux.instruct.ExecutorResult;
import cn.doanything.payment.application.flux.instruct.FluxInstructExecutor;
import cn.doanything.payment.domain.flux.AssetFluxOrder;
import cn.doanything.payment.domain.flux.BalanceAssetFluxInstruct;
import cn.doanything.payment.domain.flux.AssetFluxInstruct;
import cn.doanything.payment.domain.flux.InstructStatus;
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
