package cn.doanything.paycore.domain.flux.chain.impl;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.domain.flux.InstructStatus;
import cn.doanything.paycore.domain.flux.chain.FluxInstructChain;
import cn.doanything.paycore.domain.flux.chain.InstructChainService;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2024/1/28
 */
@Service
public class InstructChainServiceImpl implements InstructChainService {
    @Override
    public void addInstruct(FluxOrder fluxOrder, FluxInstruction fluxInstruction) {
        FluxInstructChain firstInstruct = fluxOrder.getFirstInstruct();
        FluxInstructChain lastInstruct = fluxOrder.getLastInstruct();
        if (firstInstruct == null) {
            firstInstruct = new FluxInstructChain(fluxInstruction);
            lastInstruct = new FluxInstructChain(fluxInstruction);
        } else {
            if (lastInstruct == null) {
                lastInstruct = new FluxInstructChain(fluxInstruction);
                firstInstruct.setNext(lastInstruct);
                lastInstruct.setPrev(firstInstruct);
            } else {
                FluxInstructChain fluxInstructChain = new FluxInstructChain(fluxInstruction);
                fluxInstructChain.setPrev(lastInstruct);
                lastInstruct.setNext(fluxInstructChain);
            }
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
    public void deleteAfterFluxInstruct(FluxOrder fluxOrder, String instructId) {
        FluxInstructChain fluxInstructChain = find(fluxOrder, instructId);
        FluxInstructChain next = fluxInstructChain.getNext();
        fluxInstructChain.setNext(null);
        while (next != null) {
            // 删除
            next = next.getNext();
        }
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
