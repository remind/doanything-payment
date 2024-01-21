package cn.doanything.payment.domain.flux;

import cn.doanything.commons.lang.Entity;
import cn.doanything.commons.lang.types.Money;
import cn.doanything.payment.types.asset.AssetInfo;
import cn.doanything.payment.types.funds.FluxAction;
import lombok.Data;

/**
 * 资产交换明细
 * @author wxj
 * 2024/1/20
 */
@Data
public class AssetFluxDetail extends Entity {

    /**
     * 交换单号
     */
    private String fluxOrderId;

    /**
     * 交换明细ID
     */
    private String fluxDetailId;

    /**
     * 金额
     */
    private Money amount;

    /**
     * 交换动作，相对于源资产
     */
    private FluxAction fluxAction;

    /**
     * 源资产
     */
    private AssetInfo srcAsset;

    /**
     * 目标资产
     */
    private AssetInfo targetAsset;

    /**
     * 状态
     */
    private FluxDetailStatus status;
}
