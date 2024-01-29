package cn.doanything.paycore.domain.flux.chain.impl;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.domain.flux.InstructStatus;
import cn.doanything.paycore.domain.flux.chain.FluxInstructChain;
import cn.doanything.paycore.domain.flux.chain.InstructChainService;
import cn.doanything.paycore.domain.repository.FluxInstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxj
 * 2024/1/28
 */
@Service
public class InstructChainServiceImpl implements InstructChainService {

    @Autowired
    private FluxInstructionRepository fluxInstructionRepository;

    @Override
    public void addInstruct(FluxOrder fluxOrder, FluxInstruction fluxInstruction) {
        FluxInstructChain firstInstruct = fluxOrder.getFirstInstruct();
        FluxInstructChain lastInstruct = fluxOrder.getLastInstruct();
        if (firstInstruct == null) {
            firstInstruct = new FluxInstructChain(fluxInstruction);
            lastInstruct = firstInstruct;
        } else {
            FluxInstructChain fluxInstructChain = new FluxInstructChain(fluxInstruction);
            fluxInstructChain.setPrev(lastInstruct);
            lastInstruct.setNext(fluxInstructChain);
            lastInstruct = fluxInstructChain;
        }
        fluxOrder.setFirstInstruct(firstInstruct);
        fluxOrder.setLastInstruct(lastInstruct);
    }

    @Override
    public void insertInstruct(FluxOrder fluxOrder, String instructId, FluxInstruction newFluxInstruction) {
        FluxInstructChain fluxInstructChain = find(fluxOrder, instructId);
        FluxInstructChain newFluxInstructChain = new FluxInstructChain(newFluxInstruction);
        newFluxInstructChain.setNext(fluxInstructChain.getNext());
        newFluxInstructChain.setPrev(fluxInstructChain);
        fluxInstructChain.setNext(newFluxInstructChain);
    }

    @Override
    public void insertInstruct(FluxOrder fluxOrder, String instructId, List<FluxInstruction> newFluxInstructions) {
        if (!CollectionUtils.isEmpty(newFluxInstructions)) {
            String afterInstructionId = instructId;
            for (FluxInstruction newFluxInstruction : newFluxInstructions) {
                insertInstruct(fluxOrder, afterInstructionId, newFluxInstruction);
                afterInstructionId = newFluxInstruction.getInstructionId();
            }
        }
    }

    @Override
    public List<String> deleteAfterFluxInstruct(FluxOrder fluxOrder, String instructId) {
        FluxInstructChain fluxInstructChain = find(fluxOrder, instructId);
        FluxInstructChain next = fluxInstructChain.getNext();
        fluxInstructChain.setNext(null);
        List<String> deleteIds = new ArrayList<>();
        while (next != null) {
            deleteIds.add(next.getInstructionId());
            next = next.getNext();
        }
        return deleteIds;
    }

    @Override
    public FluxInstruction getExecuteFluxInstruct(FluxOrder fluxOrder) {
        FluxInstructChain fluxInstructChain = fluxOrder.getFirstInstruct();
        while (fluxInstructChain != null) {
            if (fluxInstructChain.getFluxInstruction().getStatus() == InstructStatus.INIT) {
                return fluxInstructChain.getFluxInstruction();
            }
            fluxInstructChain = fluxInstructChain.getNext();
        }
        return null;
    }

    private FluxInstructChain find(FluxOrder fluxOrder, String instructId) {
        FluxInstructChain fluxInstructChain = fluxOrder.getFirstInstruct();
        while (fluxInstructChain != null) {
            if (fluxInstructChain.getInstructionId().equals(instructId)) {
                return fluxInstructChain;
            }
            fluxInstructChain = fluxInstructChain.getNext();
        }
        return null;
    }
}
