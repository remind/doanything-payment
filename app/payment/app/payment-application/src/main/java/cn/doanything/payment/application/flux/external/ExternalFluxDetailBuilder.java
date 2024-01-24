package cn.doanything.payment.application.flux.external;

import cn.doanything.payment.application.flux.FluxDetailBuilder;
import cn.doanything.payment.domain.flux.AssetFluxOrder;
import cn.doanything.payment.domain.flux.ExternalAssetFluxInstruct;
import cn.doanything.payment.types.asset.BelongTo;
import cn.doanything.payment.types.funds.FluxAction;
import cn.doanything.payment.types.funds.FundActionType;
import cn.doanything.payment.types.funds.FundDetail;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wxj
 * 2024/1/21
 */
@Component
public class ExternalFluxDetailBuilder implements FluxDetailBuilder {

    @Override
    public void build(AssetFluxOrder fluxOrder, FundDetail fundDetail) {
        if (fundDetail.getActionType() == FundActionType.FUND_CHANGE) {
            buildFundChangeDetail(fluxOrder, fundDetail);
        }
    }

    private void buildFundChangeDetail(AssetFluxOrder fluxOrder,  FundDetail fundDetail) {
        ExternalAssetFluxInstruct fluxInstruct = new ExternalAssetFluxInstruct();
        fluxInstruct.setAssetInfo(fundDetail.getAssetInfo());
        fluxInstruct.setFluxAction(fundDetail.getBelongTo() == BelongTo.PAYER ? FluxAction.REDUCE : FluxAction.INCREASE);
        fluxInstruct.setAmount(fundDetail.getAmount());
        fluxInstruct.setFundDetailIds(List.of(fundDetail.getDetailId()));
        fluxOrder.addDetail(fluxInstruct);
    }


}
