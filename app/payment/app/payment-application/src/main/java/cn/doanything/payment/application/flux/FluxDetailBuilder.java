package cn.doanything.payment.application.flux;

import cn.doanything.payment.domain.BasePayment;
import cn.doanything.payment.domain.flux.AssetFluxFlow;
import cn.doanything.payment.domain.flux.AssetFluxOrder;
import cn.doanything.payment.types.funds.FundDetail;

/**
 * @author wxj
 * 2024/1/21
 */
public interface FluxDetailBuilder {

    void build(BasePayment payment, AssetFluxOrder fluxOrder, AssetFluxFlow assetFluxFlow);
}
