package cn.doanything.payment.application.flux.balance;

import cn.doanything.payment.application.flux.FluxDetailBuilder;
import cn.doanything.payment.domain.flux.AssetFluxOrder;
import cn.doanything.payment.types.funds.FundDetail;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * 2024/1/21
 */
@Component
public class BalanceFluxDetailBuilder implements FluxDetailBuilder {

    @Override
    public void build(AssetFluxOrder fluxOrder, FundDetail fundDetail) {

    }
}
