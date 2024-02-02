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
     * 收款方ID
     */
    private String payeeId;

    /**
     * 收款方ID
     */
    private String payerId;
}
