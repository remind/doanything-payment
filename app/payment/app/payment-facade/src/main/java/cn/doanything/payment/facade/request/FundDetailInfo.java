package cn.doanything.payment.facade.request;

import cn.doanything.commons.lang.types.Money;
import cn.doanything.payment.types.asset.AssetInfo;
import lombok.Data;

/**
 * 资金信息
 * @author remind
 * 2023年07月14日 20:31
 */
@Data
public class FundDetailInfo {

    /**
     * 资产用户ID
     */
    private String memberId;

    /**
     * 金额
     */
    private Money amount;

    /**
     * 资产信息
     */
    private AssetInfo assetInfo;
}
