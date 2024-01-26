package cn.doanything.paycore.domain.flux;

import cn.doanything.commons.lang.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产交换单
 *
 * @author wxj
 * 2024/1/20
 */

public class FluxOrder extends Entity {

    /**
     * 支付总单号
     */
    @Setter
    @Getter
    private String paymentId;

    /**
     * 支付订单号
     */
    @Setter
    @Getter
    private String orderId;

    /**
     * 交换ID
     */
    @Setter
    @Getter
    private String fluxOrderId;

    /**
     * 交换状态
     */
    @Setter
    @Getter
    private InstructStatus status;

    private FluxInstructChain first = null;

    private FluxInstructChain last = null;

    private List<String> newFluxInstructIds = new ArrayList<>();

    private List<String> updateFluxInstructIds = new ArrayList<>();
    private List<String> deleteFluxInstructIds = new ArrayList<>();

    public void initFluxInstructs(List<FluxInstruction> fluxInstructions) {
        fluxInstructions.forEach(this::addFluxInstruct);
    }

    public void addFluxInstruct(FluxInstruction fluxInstruction) {
        if (first == null) {
            first = new FluxInstructChain(fluxInstruction);
            last = new FluxInstructChain(fluxInstruction);
        } else {
            if (last == null) {
                last = new FluxInstructChain(fluxInstruction);
                first.setNext(last);
                last.setPrev(first);
            } else {
                FluxInstructChain fluxInstructChain = new FluxInstructChain(fluxInstruction);
                fluxInstructChain.setPrev(last);
                last.setNext(fluxInstructChain);
            }
        }
        newFluxInstructIds.add(fluxInstruction.getInstructionId());
    }

    public void insertFluxInstruct(FluxInstruction fluxInstruction, FluxInstruction newFluxInstruction) {
        FluxInstructChain fluxInstructChain = find(fluxInstruction);
        FluxInstructChain newFluxInstructChain = new FluxInstructChain(newFluxInstruction);
        newFluxInstructChain.setNext(fluxInstructChain.getNext());
        newFluxInstructChain.setPrev(fluxInstructChain);
        fluxInstructChain.setNext(newFluxInstructChain);
        newFluxInstructIds.add(fluxInstruction.getInstructionId());
    }

    public void deleteAfterFluxInstruct(FluxInstruction fluxInstruction) {
        FluxInstructChain fluxInstructChain = find(fluxInstruction);
        FluxInstructChain next = fluxInstructChain.getNext();
        fluxInstructChain.setNext(null);
        while (next != null) {
            deleteFluxInstructIds.add(next.getFluxInstruction().getFluxOrderId());
            next = next.getNext();
        }
    }

    public FluxInstruction find(String fluxInstructId) {
        FluxInstructChain fluxInstructChain = first;
        while (fluxInstructChain != null) {
            if (fluxInstructChain.getFluxInstruction().getInstructionId().equals(fluxInstructId)) {
                return fluxInstructChain.getFluxInstruction();
            }
            fluxInstructChain = fluxInstructChain.getNext();
        }
        return null;
    }

    public FluxInstruction getExecuteFluxInstruct() {
        FluxInstructChain fluxInstructChain = first;
        while (fluxInstructChain != null) {
            if (fluxInstructChain.getFluxInstruction().getStatus() == InstructStatus.INIT) {
                return fluxInstructChain.getFluxInstruction();
            }
            fluxInstructChain = fluxInstructChain.getNext();
        }
        return null;
    }

    public List<FluxInstruction> getAllFluxInstructs() {
        List<FluxInstruction> fluxInstructions = new ArrayList<>();
        FluxInstructChain fluxInstructChain = first;
        while (fluxInstructChain != null) {
            fluxInstructions.add(fluxInstructChain.getFluxInstruction());
            fluxInstructChain = fluxInstructChain.getNext();
        }
        return fluxInstructions;
    }



    private FluxInstructChain find(FluxInstruction fluxInstruction) {
        FluxInstructChain fluxInstructChain = first;
        while (fluxInstructChain != null) {
            if (fluxInstructChain.getFluxInstruction().getInstructionId().equals(fluxInstruction.getInstructionId())) {
                return fluxInstructChain;
            }
            fluxInstructChain = fluxInstructChain.getNext();
        }
        return null;
    }

    @Getter
    private static class FluxInstructChain {

        private final FluxInstruction fluxInstruction;
        @Setter
        private FluxInstructChain prev;
        @Setter
        private FluxInstructChain next;

        public FluxInstructChain(FluxInstruction fluxInstruction) {
            this.fluxInstruction = fluxInstruction;
        }

    }

}
