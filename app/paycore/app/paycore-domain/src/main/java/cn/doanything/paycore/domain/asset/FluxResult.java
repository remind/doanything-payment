package cn.doanything.paycore.domain.asset;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.InstructStatus;
import cn.doanything.paycore.types.PayStatus;
import lombok.Data;

import java.util.List;

/**
 * @author wxj
 * 2024/1/26
 */
@Data
public class FluxResult {

    private PayStatus status;

    private List<FluxInstruction> newFluxInstructions;
}
