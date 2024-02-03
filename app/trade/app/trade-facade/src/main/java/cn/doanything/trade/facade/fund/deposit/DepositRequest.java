package cn.doanything.trade.facade.fund.deposit;

import cn.doanything.commons.payment.asset.AssetInfo;
import cn.doanything.trade.facade.TradeRequest;
import lombok.Data;

/**
 * @author wxj
 * 2024/2/3
 */
@Data
public class DepositRequest extends TradeRequest {

    /**
     * 会员ID
     */
    private String memberId;

    /**
     * 账户号
     */
    private String accountNo;

    /**
     * 充值资产
     */
    private AssetInfo depositAsset;

    /**
     * 备注
     */
    private String memo;
}
