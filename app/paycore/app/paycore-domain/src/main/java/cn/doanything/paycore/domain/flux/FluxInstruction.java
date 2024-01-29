package cn.doanything.paycore.domain.flux;

import cn.doanything.commons.lang.types.Money;
import cn.doanything.paycore.types.asset.AssetType;
import cn.doanything.paycore.types.funds.FundAction;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 交换指令
 * @author wxj
 * 2024/1/25
 */
@Data
public class FluxInstruction {

    /**
     * 支付总单ID
     */
    private String paymentId;

    /**
     * 支付订单ID
     */
    private String payId;

    /**
     * 资产交换订单ID
     */
    private String fluxOrderId;

    /**
     * 指令ID
     */
    private String instructionId;

    /**
     * 指令类型
     */
    private InstructionType type;

    private String relatedId;

    private Money amount;

    private String fundDetailId;

    private InstructStatus status;

    private FundAction fundAction;

    private AssetType assetType;

    private String assetInfo;

    private Map<String, String> extMap = new HashMap<>();

    public String getExtValue(String key) {
        return extMap == null ? null : extMap.get(key);
    }

    public void putExtValue(String key, String value) {
        extMap.put(key, value);
    }

}
