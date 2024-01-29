package cn.doanything.paycore.domain.flux;

import cn.doanything.commons.lang.Entity;
import cn.doanything.paycore.domain.flux.chain.FluxInstructChain;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产交换单
 *
 * @author wxj
 * 2024/1/20
 */
@Data
public class FluxOrder extends Entity {

    /**
     * 支付总单ID
     */
    private String paymentId;

    /**
     * 支付订单ID
     */
    private String payId;

    /**
     * 交换ID
     */
    private String fluxOrderId;

    /**
     * 交换状态
     */
    private FluxOrderStatus status;

    /**
     * 关联ID
     */
    private String relationId;

    private FluxInstructChain firstInstruct = null;

    private FluxInstructChain lastInstruct = null;

    public FluxInstruction find(String fluxInstructId) {
        FluxInstructChain fluxInstructChain = firstInstruct;
        while (fluxInstructChain != null) {
            if (fluxInstructChain.getFluxInstruction().getInstructionId().equals(fluxInstructId)) {
                return fluxInstructChain.getFluxInstruction();
            }
            fluxInstructChain = fluxInstructChain.getNext();
        }
        return null;
    }

    public List<FluxInstruction> getAllFluxInstructs() {
        List<FluxInstruction> fluxInstructions = new ArrayList<>();
        FluxInstructChain fluxInstructChain = firstInstruct;
        while (fluxInstructChain != null) {
            fluxInstructions.add(fluxInstructChain.getFluxInstruction());
            fluxInstructChain = fluxInstructChain.getNext();
        }
        return fluxInstructions;
    }

}
