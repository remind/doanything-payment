package cn.doanything.paycore.domain.flux;

import cn.doanything.commons.lang.types.Money;
import lombok.Data;

/**
 * 资产交换指令
 * @author wxj
 * 2024/1/25
 */
@Data
public class AssetFluxInstruct {

    /**
     * 资产交换订单ID
     */
    private String fluxOrderId;

    /**
     * 指令ID
     */
    private String fluxInstructId;

    private InstructType instructType;

    private String relatedFluxInstructId;

    private Money amount;

    private String fundDetailId;

    private InstructStatus status;


}
