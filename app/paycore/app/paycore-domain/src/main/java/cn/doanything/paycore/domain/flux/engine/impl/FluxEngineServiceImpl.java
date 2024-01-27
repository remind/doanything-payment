package cn.doanything.paycore.domain.flux.engine.impl;

import cn.doanything.paycore.domain.asset.AssetFluxFactory;
import cn.doanything.paycore.domain.asset.FluxInstructionExecutor;
import cn.doanything.paycore.domain.asset.FluxResult;
import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.domain.flux.InstructStatus;
import cn.doanything.paycore.domain.flux.engine.FluxEngineService;
import cn.doanything.paycore.domain.repository.FluxInstructionRepository;
import cn.doanything.paycore.types.PayStatus;
import cn.doanything.paycore.types.asset.AssetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

    @Override
    public FluxInstruction execute(FluxOrder fluxOrder) {
        FluxInstruction executeInstruction = fluxOrder.getExecuteFluxInstruct();
        while (executeInstruction != null) {
            AssetType assetType = executeInstruction.getAssetType();
            FluxInstructionExecutor instructionExecutor = assetFluxFactory.getFluxInstructionExecutor(assetType);
            FluxResult result = instructionExecutor.execute(fluxOrder, executeInstruction);
            executeInstruction.setStatus(convertToInstructStatus(result.getStatus()));
            if (result.getStatus() == PayStatus.SUCCESS) {
                insertNewFluxInstruct(fluxOrder, executeInstruction, result.getNewFluxInstructions());
                instructionRepository.reStore(executeInstruction);
                executeInstruction = fluxOrder.getExecuteFluxInstruct();
            } else if (result.getStatus() == PayStatus.FAIL) {
                executeInstruction = fluxOrder.getExecuteFluxInstruct();
                instructionRepository.reStore(executeInstruction);
            } else {
                executeInstruction = null;
            }
        }
        return null;
    }

    private InstructStatus convertToInstructStatus(PayStatus payStatus) {
        switch (payStatus) {
            case SUCCESS:
                return InstructStatus.SUCCESS;
            case FAIL:
                return InstructStatus.FAIL;
            case PROCESSING:
                return InstructStatus.PROCESSING;
            default:
                return null;
        }
    }

    private void insertNewFluxInstruct(FluxOrder fluxOrder, FluxInstruction fluxInstruction, List<FluxInstruction> newFluxInstructions) {
        if (!CollectionUtils.isEmpty(newFluxInstructions)) {
            FluxInstruction afterFluxInstruction = fluxInstruction;
            for (FluxInstruction newFluxInstruction : newFluxInstructions) {
                fluxOrder.insertFluxInstruct(afterFluxInstruction, newFluxInstruction);
                afterFluxInstruction = newFluxInstruction;
            }
        }
    }
}
