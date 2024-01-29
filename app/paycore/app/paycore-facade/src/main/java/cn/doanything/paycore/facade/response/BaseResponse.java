package cn.doanything.paycore.facade.response;

import cn.doanything.paycore.types.PayResult;
import lombok.Data;

/**
 * @author wxj
 * 2024/1/29
 */
@Data
public class BaseResponse {

    /**
     * 支付总单号
     */
    private String paymentId;

    /**
     * 支付结果
     */
    private PayResult result;
}
