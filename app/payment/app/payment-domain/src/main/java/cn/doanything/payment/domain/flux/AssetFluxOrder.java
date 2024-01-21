package cn.doanything.payment.domain.flux;

import cn.doanything.commons.lang.Entity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产交换单
 * @author wxj
 * 2024/1/20
 */
@Data
public class AssetFluxOrder extends Entity {

    /**
     * 支付总单号
     */
    private String paymentId;

    /**
     * 支付订单号
     */
    private String orderId;

    /**
     * 交换ID
     */
    private String fluxOrderId;

    /**
     * 交换状态
     */
    private FluxOrderStatus status;

    /**
     * 交换明细
     */
    private List<AssetFluxDetail> assetFluxDetails = new ArrayList<>();

    public void addDetail(AssetFluxDetail assetFluxDetail) {
        assetFluxDetail.setFluxOrderId(this.fluxOrderId);
        this.assetFluxDetails.add(assetFluxDetail);
    }
}
