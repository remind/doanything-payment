package cn.doanything.paycore.application.flux.instruct;

import cn.doanything.paycore.domain.flux.AssetFluxInstruct;
import cn.doanything.paycore.domain.flux.InstructStatus;
import lombok.Data;

import java.util.List;

/**
 * @author wxj
 * 2024/1/26
 */
@Data
public class ExecutorResult {

    private InstructStatus status;

    private List<AssetFluxInstruct> newAssetFluxInstructs;
}
