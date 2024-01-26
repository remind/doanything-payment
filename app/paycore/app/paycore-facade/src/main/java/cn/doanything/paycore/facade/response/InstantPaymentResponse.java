package cn.doanything.paycore.facade.response;

import cn.doanything.paycore.types.OrderStatus;
import lombok.Data;

/**
 * @author remind
 * 2023年07月14日 20:28
 */
@Data
public class InstantPaymentResponse {

    private String paymentId;

    private String orderId;

    private OrderStatus orderStatus;

}
