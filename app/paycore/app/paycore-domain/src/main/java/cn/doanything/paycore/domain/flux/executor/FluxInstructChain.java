package cn.doanything.paycore.domain.flux.executor;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import lombok.Getter;
import lombok.Setter;

/**
 * 交换指令链
 * @author wxj
 * 2024/1/26
 */
@Getter
public class FluxInstructChain {

    /**
     * 交换指令
     */
    private final FluxInstruction fluxInstruction;

    /**
     * 上一个
     */
    @Setter
    private FluxInstructChain prev;

    /**
     * 下一个
     */
    @Setter
    private FluxInstructChain next;

    public FluxInstructChain(FluxInstruction fluxInstruction) {
        this.fluxInstruction = fluxInstruction;
    }

    public String getInstructionId() {
        return fluxInstruction.getInstructionId();
    }
}
