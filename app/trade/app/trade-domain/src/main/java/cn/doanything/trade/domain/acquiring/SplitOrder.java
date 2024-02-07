package cn.doanything.trade.domain.acquiring;

import cn.doanything.commons.lang.Entity;
import cn.doanything.commons.lang.types.Money;
import cn.doanything.types.status.SplitOrderStatus;
import lombok.Data;

/**
 * 分账订单
 * @author wxj
 * 2024/2/7
 */
@Data
public class SplitOrder extends Entity {

    /**
     * 分账ID
     */
    private String splitId;

    /**
     * 交易ID
     */
    private String tradeId;

    /**
     * 分账业务单号
     */
    private String splitBizNo;

    /**
     * 分账金额
     */
    private Money amount;

    /**
     * 状态
     */
    private SplitOrderStatus status;

}
