package cn.doanything.payment.types.funds;

import cn.doanything.commons.lang.types.Money;
import cn.doanything.payment.types.asset.AssetInfo;
import cn.doanything.payment.types.asset.BelongTo;
import lombok.Data;

/**
 * 资金明细
 * @author wxj
 * 2024/1/15
 */
@Data
public class FundDetail {

    /**
     * 支付总单ID
     */
    private String paymentId;

    /**
     * 支付单ID
     */
    private String orderId;

    /**
     * 详情ID
     */
    private String detailId;

    /**
     * 资产所有人ID
     */
    private String memberId;

    /**
     * 所属方
     */
    private BelongTo belongTo;

    /**
     * 资金动作类型
     */
    private FundActionType actionType;

    /**
     * 金额
     */
    private Money amount;

    /**
     * 资产信息
     */
    private AssetInfo assetInfo;
}
