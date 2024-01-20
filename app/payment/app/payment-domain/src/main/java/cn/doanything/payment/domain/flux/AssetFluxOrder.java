package cn.doanything.payment.domain.flux;

import cn.doanything.commons.lang.Entity;
import lombok.Data;

/**
 * @author wxj
 * 2024/1/20
 */
@Data
public class AssetFluxOrder extends Entity {

    /**
     * 支付总单号
     */
    private String paymentId;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 交换ID
     */
    private String fluxId;

    /**
     * 交换状态
     */
    private FluxStatus status;
}
