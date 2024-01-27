package cn.doanything.paycore.domain.flux;

import cn.doanything.commons.lang.types.Money;
import cn.doanything.paycore.types.asset.AssetType;
import lombok.Data;

/**
 * 交换指令
 * @author wxj
 * 2024/1/25
 */
@Data
public abstract class FluxInstruction {

    /**
     * 资产交换订单ID
     */
    private String fluxOrderId;

    /**
     * 指令ID
     */
    private String instructionId;

    private InstructionType instructionType;

    private String relatedInstructionId;

    private Money amount;

    private String fundDetailId;

    private InstructStatus status;


    public abstract AssetType getAssetType();

}
