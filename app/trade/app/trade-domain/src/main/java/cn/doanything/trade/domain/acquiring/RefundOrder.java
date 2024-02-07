package cn.doanything.trade.domain.acquiring;

import cn.doanything.commons.lang.Entity;
import cn.doanything.commons.lang.types.Money;
import cn.doanything.types.status.RefundOrderStatus;
import lombok.Data;

/**
 * 退款订单
 * @author wxj
 * 2024/2/7
 */
@Data
public class RefundOrder extends Entity {

    /**
     * 分账ID
     */
    private String refundId;

    /**
     * 交易ID
     */
    private String tradeId;

    /**
     * 退款业务单号
     */
    private String refundBizNo;

    /**
     * 退款金额
     */
    private Money amount;

    /**
     * 状态
     */
    private RefundOrderStatus status;
}
