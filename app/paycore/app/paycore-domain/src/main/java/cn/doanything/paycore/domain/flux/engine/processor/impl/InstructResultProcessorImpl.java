package cn.doanything.paycore.domain.flux.engine.processor.impl;

import cn.doanything.paycore.domain.asset.FluxResult;
import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.domain.flux.InstructStatus;
import cn.doanything.paycore.domain.flux.InstructionType;
import cn.doanything.paycore.domain.flux.chain.InstructChainService;
import cn.doanything.paycore.domain.flux.engine.processor.InstructResultProcessor;
import cn.doanything.paycore.domain.repository.FluxInstructionRepository;
import cn.doanything.paycore.domain.service.IdGeneratorService;
import cn.doanything.paycore.types.IdType;
import cn.doanything.paycore.types.PayStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author wxj
 * 2024/1/29
 */
@Service
public class InstructResultProcessorImpl implements InstructResultProcessor {

    @Autowired
    private FluxInstructionRepository instructionRepository;

    @Autowired
    private InstructChainService instructChainService;

    @Autowired
    private IdGeneratorService idGeneratorService;

    @Override
    public boolean process(FluxOrder fluxOrder, FluxInstruction fluxInstruction, FluxResult fluxResult) {
        boolean isContinue = true;
        fluxInstruction.setStatus(convertToInstructStatus(fluxResult.getStatus()));
        switch (fluxResult.getStatus()) {
            case SUCCESS:
                successProcess(fluxOrder, fluxInstruction, fluxResult);
                break;
            case FAIL:
                failProcess(fluxOrder, fluxInstruction, fluxResult);
            default:
                isContinue = false;
                break;
        }
        instructionRepository.reStore(fluxInstruction);
        return isContinue;
    }

    private void successProcess(FluxOrder fluxOrder, FluxInstruction fluxInstruction, FluxResult fluxResult) {
        if (!CollectionUtils.isEmpty(fluxResult.getNewFluxInstructions())) {
            for (FluxInstruction newFluxInstruction : fluxResult.getNewFluxInstructions()) {
                newFluxInstruction.setPaymentId(fluxInstruction.getPaymentId());
                newFluxInstruction.setPayId(fluxInstruction.getPayId());
                newFluxInstruction.setFluxOrderId(fluxOrder.getFluxOrderId());
                newFluxInstruction.setInstructionId(idGeneratorService.genIdByRelateId(fluxInstruction.getInstructionId(), IdType.FLUX_INSTRUCT_ID));
                newFluxInstruction.setStatus(InstructStatus.INIT);
            }
            List<FluxInstruction> addInstructions = instructChainService.insertInstruct(fluxOrder, fluxInstruction, fluxResult.getNewFluxInstructions());
            addInstructions.forEach(instruction -> {
                instructionRepository.store(instruction);
            });
        }
    }

    private void failProcess(FluxOrder fluxOrder, FluxInstruction fluxInstruction, FluxResult fluxResult) {
        if (fluxInstruction.getType() == InstructionType.FORWARD) {
            instructionRepository.reStore(fluxInstruction);
        }
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
