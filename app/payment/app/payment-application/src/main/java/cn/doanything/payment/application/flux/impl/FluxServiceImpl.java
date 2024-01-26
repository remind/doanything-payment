package cn.doanything.payment.application.flux.impl;

import cn.doanything.payment.application.flux.FluxService;
import cn.doanything.payment.application.flux.instruct.ExecutorResult;
import cn.doanything.payment.application.flux.instruct.FluxInstructExecutor;
import cn.doanything.payment.domain.flux.AssetFluxOrder;
import cn.doanything.payment.domain.flux.BalanceAssetFluxInstruct;
import cn.doanything.payment.domain.flux.AssetFluxInstruct;
import cn.doanything.payment.domain.flux.InstructStatus;
import cn.doanything.payment.domain.flux.service.AssetFluxOrderDomainService;
import cn.doanything.payment.types.PayResult;
import cn.doanything.payment.types.PayStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wxj
 * 2024/1/26
 */
@Service
public class FluxServiceImpl implements FluxService {

    @Resource(name = "balanceInstructExecutor")
    private FluxInstructExecutor balanceInstructExecutor;

    @Resource(name = "externalFluxInstructExecutor")
    private FluxInstructExecutor externalFluxInstructExecutor;

    @Autowired
    private AssetFluxOrderDomainService fluxOrderDomainService;

    @Override
    public PayResult process(AssetFluxOrder fluxOrder) {
        ExecutorResult result = null;
        AssetFluxInstruct executeAssetFluxInstruct = fluxOrder.getExecuteFluxInstruct();
        while (executeAssetFluxInstruct != null) {
            if (executeAssetFluxInstruct instanceof BalanceAssetFluxInstruct) {
                result = balanceInstructExecutor.execute(fluxOrder, executeAssetFluxInstruct);
            } else {
                result = externalFluxInstructExecutor.execute(fluxOrder, executeAssetFluxInstruct);
            }
            executeAssetFluxInstruct.setStatus(result.getStatus());
            if (result.getStatus() == InstructStatus.SUCCESS) {
                insertNewFluxInstruct(fluxOrder, executeAssetFluxInstruct, result.getNewAssetFluxInstructs());
                executeAssetFluxInstruct = fluxOrder.getExecuteFluxInstruct();
            } else if (result.getStatus() == InstructStatus.FAIL) {
                fluxOrderDomainService.failHandle(fluxOrder, executeAssetFluxInstruct);
                executeAssetFluxInstruct = fluxOrder.getExecuteFluxInstruct();
            } else {
                executeAssetFluxInstruct = null;
            }
        }
        return convertToPayResult(fluxOrder, result);
    }

    private void insertNewFluxInstruct(AssetFluxOrder fluxOrder, AssetFluxInstruct assetFluxInstruct, List<AssetFluxInstruct> newAssetFluxInstructs) {
        AssetFluxInstruct afterAssetFluxInstruct = assetFluxInstruct;
        for (AssetFluxInstruct newAssetFluxInstruct : newAssetFluxInstructs) {
            fluxOrder.insertFluxInstruct(afterAssetFluxInstruct, newAssetFluxInstruct);
            afterAssetFluxInstruct = newAssetFluxInstruct;
        }
    }

    private PayResult convertToPayResult(AssetFluxOrder fluxOrder, ExecutorResult executorResult) {
        PayResult payResult = new PayResult();

        if (executorResult == null || executorResult.getStatus() == InstructStatus.SUCCESS) {
            payResult.setPayStatus(PayStatus.SUCCESS);
        } else if (executorResult.getStatus() == InstructStatus.FAIL) {
            payResult.setPayStatus(PayStatus.FAIL);
        } else {
            payResult.setPayStatus(PayStatus.PROCESSING);
        }
        return payResult;
    }
}
