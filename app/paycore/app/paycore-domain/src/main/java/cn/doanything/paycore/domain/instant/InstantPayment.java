package cn.doanything.paycore.domain.instant;

import cn.doanything.paycore.domain.payorder.BasePayOrder;
import cn.doanything.paycore.domain.BasePayment;
import cn.doanything.paycore.domain.payorder.PayOrder;
import cn.doanything.paycore.domain.payorder.RefundOrder;
import cn.doanything.paycore.types.funds.FundDetail;
import lombok.Data;

import java.util.ArrayList;
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
    private List<RefundOrder> refundOrderList = new ArrayList<>();

    @Override
    public BasePayOrder getBasePayOrder() {
        return this.payOrder;
    }

    @Override
    public FundDetail getFundDetail(String orderId, String fundDetailId) {
        if (payOrder.getOrderId().equals(orderId)) {
            return payOrder.getFundDetail(fundDetailId);
        }
        return null;
    }
}
