package cn.doanything.trade.domain.fund;

import cn.doanything.trade.domain.TradeOrder;
import lombok.Data;

/**
 * @author wxj
 * 2024/1/31
 */
@Data
public class FundOrder extends TradeOrder {

    /**
     * 发起人ID
     * 分为：提现人、转账人、充值人、代发出款户、代扣收款户
     */
    private String memberId;

    /**
     * 状态
     */
    private FundOrderStatus status;
}
