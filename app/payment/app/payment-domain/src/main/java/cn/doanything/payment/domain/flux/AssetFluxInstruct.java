package cn.doanything.payment.domain.flux;

import cn.doanything.commons.lang.types.Money;
import lombok.Data;

import java.util.List;

/**
 * 交换指令
 * @author wxj
 * 2024/1/23
 */
@Data
public class AssetFluxInstruct {

    private String fluxOrderId;

    private String fluxFlowId;

    private String fluxInstructId;

    private InstructType instructType;

    private String relatedFluxInstructId;

    private Money amount;

    private List<String> fundDetailIds;

}
