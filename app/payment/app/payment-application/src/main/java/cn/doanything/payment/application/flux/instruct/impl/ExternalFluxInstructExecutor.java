package cn.doanything.payment.application.flux.instruct.impl;

import cn.doanything.payment.application.flux.instruct.ExecutorResult;
import cn.doanything.payment.application.flux.instruct.FluxInstructExecutor;
import cn.doanything.payment.domain.PaymentConstants;
import cn.doanything.payment.domain.flux.*;
import cn.doanything.payment.types.asset.BalanceAsset;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wxj
 * 2024/1/26
 */
@Component
public class ExternalFluxInstructExecutor implements FluxInstructExecutor {
    @Override
    public ExecutorResult execute(AssetFluxOrder fluxOrder, AssetFluxInstruct assetFluxInstruct) {
        ExternalAssetFluxInstruct externalFluxInstruct = (ExternalAssetFluxInstruct) assetFluxInstruct;
        ExecutorResult result = new ExecutorResult();
        String clearingAccountNo = "clearingAccountNo";
        externalFluxInstruct.setClearingAccountNo(clearingAccountNo);
        result.setStatus(InstructStatus.SUCCESS);
        result.setNewAssetFluxInstructs(List.of(buildClearingFluxInstruct(externalFluxInstruct, externalFluxInstruct.getClearingAccountNo())));
        return result;
    }

    private BalanceAssetFluxInstruct buildClearingFluxInstruct(AssetFluxInstruct assetFluxInstruct, String clearAccountNo) {
        BalanceAssetFluxInstruct newFluxInstruct = new BalanceAssetFluxInstruct();
        newFluxInstruct.setFluxOrderId(assetFluxInstruct.getFluxOrderId());
        newFluxInstruct.setInstructType(InstructType.FORWARD);
        newFluxInstruct.setRelatedFluxInstructId(assetFluxInstruct.getFluxInstructId());
        newFluxInstruct.setFundDetailId(assetFluxInstruct.getFundDetailId());
        newFluxInstruct.setAmount(assetFluxInstruct.getAmount());
        newFluxInstruct.setDebitAsset(new BalanceAsset(PaymentConstants.INNER_MEMBER_ID, clearAccountNo));
        newFluxInstruct.setCreditAsset(new BalanceAsset(PaymentConstants.INNER_MEMBER_ID, PaymentConstants.TRANSITION_ACCOUNT));
        return newFluxInstruct;
    }

}
