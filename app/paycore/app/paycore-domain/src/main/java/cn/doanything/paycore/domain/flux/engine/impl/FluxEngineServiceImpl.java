package cn.doanything.paycore.domain.flux.engine.impl;

import cn.doanything.paycore.domain.asset.FluxInstructionExecutor;
import cn.doanything.paycore.domain.asset.FluxResult;
import cn.doanything.paycore.domain.asset.factory.AssetFluxFactory;
import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.domain.flux.InstructStatus;
import cn.doanything.paycore.domain.flux.chain.InstructChainService;
import cn.doanything.paycore.domain.flux.engine.FluxEngineService;
import cn.doanything.paycore.domain.repository.FluxInstructionRepository;
import cn.doanything.paycore.types.PayResult;
import cn.doanything.paycore.types.PayStatus;
import cn.doanything.paycore.types.asset.AssetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2024/1/27
 */
@Service
public class FluxEngineServiceImpl implements FluxEngineService {

    @Autowired
    private AssetFluxFactory assetFluxFactory;

    @Autowired
    private FluxInstructionRepository instructionRepository;

    @Autowired
    private InstructChainService instructChainService;

    @Override
    public PayResult process(FluxOrder fluxOrder) {
        FluxInstruction executeInstruction = instructChainService.getExecuteFluxInstruct(fluxOrder);
        FluxResult result = null;
        while (executeInstruction != null) {
            AssetType assetType = executeInstruction.getAssetType();
            FluxInstructionExecutor instructionExecutor = assetFluxFactory.getFluxInstructionExecutor(assetType);
            result = instructionExecutor.execute(fluxOrder, executeInstruction);
            executeInstruction.setStatus(convertToInstructStatus(result.getStatus()));
            if (result.getStatus() == PayStatus.SUCCESS) {
                instructChainService.insertInstruct(fluxOrder, executeInstruction.getInstructionId(), result.getNewFluxInstructions());
                instructionRepository.reStore(executeInstruction);
                executeInstruction = instructChainService.getExecuteFluxInstruct(fluxOrder);
            } else if (result.getStatus() == PayStatus.FAIL) {
                instructionRepository.reStore(executeInstruction);
                executeInstruction = instructChainService.getExecuteFluxInstruct(fluxOrder);
            } else {
                executeInstruction = null;
            }
            // TODO 需要判断fluxOrder状态是否为已撤消，如果是就要走逆向流程
        }
        return convertToPayResult(result);
    }

    private PayResult convertToPayResult(FluxResult fluxResult) {
        PayResult payResult = new PayResult();
        if (fluxResult == null) {
            payResult.setPayStatus(PayStatus.SUCCESS);
        } else {
            payResult.setPayStatus(fluxResult.getStatus());
            payResult.setResultMessage(fluxResult.getResultMessage());
            payResult.setResultCode(fluxResult.getResultCode());
        }
        return payResult;
    }

    private InstructStatus convertToInstructStatus(PayStatus payStatus) {
        switch (payStatus) {
            case SUCCESS:
                return InstructStatus.SUCCESS;
            case FAIL:
                return InstructStatus.FAIL;
            case PROCESS:
                return InstructStatus.PROCESS;
            default:
                return null;
        }
    }
}
