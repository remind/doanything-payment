package cn.doanything.trade.domain.fund;

import cn.doanything.commons.payment.asset.AssetInfo;
import cn.doanything.trade.domain.TradeOrder;
import cn.doanything.trade.status.FundOrderStatus;
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
     * 收款方资产
     */
    private AssetInfo payeeAsset;

    /**
     * 收款方ID
     */
    private String payerId;

    /**
     * 付款方资产
     */
    private AssetInfo payerAsset;

    /**
     * 状态
     */
    private FundOrderStatus status;
}
