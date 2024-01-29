package cn.doanything.paycore.domain.flux.chain;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;

import java.util.List;

/**
 * 指令链服务，只维护内存中的链，不动仓储
 * @author wxj
 * 2024/1/28
 */
public interface InstructChainService {

    /**
     * 新增指令到末尾
     * @param fluxOrder
     * @param fluxInstruction
     */
    void addInstruct(FluxOrder fluxOrder, FluxInstruction fluxInstruction);

    /**
     * 插入指令在指定指令之后
     * @param fluxOrder
     * @param instructId
     * @param newFluxInstruction
     */
    void insertInstruct(FluxOrder fluxOrder, String instructId, FluxInstruction newFluxInstruction);

    /**
     * 插入指令在指定指令之后
     * @param fluxOrder
     * @param instructId
     * @param newFluxInstructions
     */
    void insertInstruct(FluxOrder fluxOrder, String instructId, List<FluxInstruction> newFluxInstructions);

    /**
     * 删除指定指令后面的指令
     * @param fluxOrder
     * @param instructId
     * @return
     */
    List<String> deleteAfterFluxInstruct(FluxOrder fluxOrder, String instructId);

    /**
     * 获取可执行指令
     * @param fluxOrder
     * @return
     */
    FluxInstruction getExecuteFluxInstruct(FluxOrder fluxOrder);
}
