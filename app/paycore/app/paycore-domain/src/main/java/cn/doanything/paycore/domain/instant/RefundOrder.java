package cn.doanything.paycore.domain.instant;

import cn.doanything.paycore.domain.BasePayOrder;

/**
 * @author wxj
 * 2024/1/15
 */
public class RefundOrder extends BasePayOrder<RefundOrderStatus> {

    /**
     * 关联订单号
     */
    private String relationId;
}
