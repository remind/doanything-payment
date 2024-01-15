package cn.doanything.payment.domain.instant;

import cn.doanything.payment.domain.BasePayOrder;

/**
 * @author wxj
 * 2024/1/15
 */
public class RefundOrder extends BasePayOrder<RefundOrderStatus> {

    /**
     * 关联订单号
     */
    private String relatedOrderId;
}
