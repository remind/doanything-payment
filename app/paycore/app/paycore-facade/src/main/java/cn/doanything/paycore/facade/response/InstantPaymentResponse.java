package cn.doanything.paycore.facade.response;

import cn.doanything.paycore.types.OrderStatus;
import lombok.Data;

/**
 * @author remind
 * 2023年07月14日 20:28
 */
@Data
public class InstantPaymentResponse extends BaseResponse {

    /**
     * 支付单号
     */
    private String payOrderId;

    /**
     * 支付单状态
     */
    private OrderStatus orderStatus;

}
