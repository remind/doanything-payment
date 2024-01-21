package cn.doanything.payment.application.flux;

import cn.doanything.payment.domain.flux.AssetFluxOrder;
import cn.doanything.payment.types.funds.FundDetail;

/**
 * @author wxj
 * 2024/1/21
 */
public interface FluxDetailBuilder {

    void build(AssetFluxOrder fluxOrder, FundDetail fundDetail);
}
