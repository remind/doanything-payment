package cn.doanything.trade.domain.payment;

import cn.doanything.commons.lang.types.Money;
import cn.doanything.commons.payment.asset.AssetInfo;
import cn.doanything.commons.payment.asset.BelongTo;
import lombok.Data;

/**
 * 支付参与方
 * @author wxj
 * 2024/2/2
 */
@Data
public class PaymentParty {

    /**
     * 会员ID
     */
    private String memberId;

    /**
     * 金额
     */
    private Money amount;

    /**
     * 所属方
     */
    private BelongTo belongTo;

    /**
     * 资产信息
     */
    private AssetInfo assetInfo;

}
