package cn.doanything.payment.types.funds;

import cn.doanything.commons.lang.types.Money;
import cn.doanything.payment.types.asset.AssetInfo;
import cn.doanything.payment.types.asset.BelongTo;
import lombok.Data;

/**
 * @author wxj
 * 2024/1/15
 */
@Data
public class FundDetail {
    /**
     * 详情ID
     */
    private String detailId;

    /**
     * 参与方ID
     */
    private String partyId;

    /**
     * 所属方
     */
    private BelongTo belongTo;

    /**
     * 资金动作
     */
    private FundsAction action;

    /**
     * 金额
     */
    private Money amount;

    /**
     * 资产信息
     */
    private AssetInfo assetInfo;
}
