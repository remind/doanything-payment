package cn.doanything.payment.domain.instant;

import cn.doanything.payment.domain.BasePayment;
import lombok.Data;

import java.util.List;

/**
 * @author wxj
 * 2024/1/15
 */
@Data
public class InstantPayment extends BasePayment {

    /**
     * 支付单
     */
    private PayOrder payOrder;

    /**
     * 退款单，可多次退款
     */
    private List<RefundOrder> refundOrderList;
}
