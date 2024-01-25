package cn.doanything.payment.domain.flux;

import cn.doanything.commons.lang.types.Money;
import lombok.Data;

import java.util.List;

/**
 * @author wxj
 * 2024/1/25
 */
@Data
public class FluxInstruct {
    private String fluxOrderId;

    private String fluxFlowId;

    private String fluxInstructId;

    private InstructType instructType;

    private String relatedFluxInstructId;

    private Money amount;

    private String fundDetailId;
}
