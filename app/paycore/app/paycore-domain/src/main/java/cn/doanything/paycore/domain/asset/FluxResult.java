package cn.doanything.paycore.domain.asset;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.types.PayStatus;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交换结果
 *
 * @author wxj
 * 2024/1/26
 */
@Data
public class FluxResult {

    /**
     * 支付状态
     */
    private PayStatus status;

    /**
     * 新增交换指令
     */
    private List<FluxInstruction> newFluxInstructions;

    /**
     * 返回结果码
     */
    private String resultCode;

    /**
     * 返回信息
     */
    private String resultMessage;

    /**
     * 扩展信息
     */
    private Map<String, String> extMap = new HashMap<>();
}
