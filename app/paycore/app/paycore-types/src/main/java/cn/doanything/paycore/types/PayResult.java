package cn.doanything.paycore.types;

import lombok.Data;

/**
 * @author wxj
 * 2024/1/26
 */
@Data
public class PayResult {

    /**
     * 支付状态
     */
    private PayStatus payStatus;

    /**
     * 返回结果码
     */
    private String resultCode;

    /**
     * 返回信息
     */
    private String resultMessage;
}
