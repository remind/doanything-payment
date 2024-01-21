package cn.doanything.payment.application.flux.external;

import cn.doanything.payment.application.flux.FluxDetailBuilder;
import cn.doanything.payment.domain.flux.AssetFluxDetail;
import cn.doanything.payment.domain.flux.AssetFluxOrder;
import cn.doanything.payment.types.asset.BelongTo;
import cn.doanything.payment.types.funds.FluxAction;
import cn.doanything.payment.types.funds.FundActionType;
import cn.doanything.payment.types.funds.FundDetail;
import org.springframework.stereotype.Component;

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
        BelongTo belongTo = fundDetail.getBelongTo();
        AssetFluxDetail fluxDetail = new AssetFluxDetail();
        if (BelongTo.PAYER.equals(belongTo)) {
            fluxDetail.setSrcAsset(fundDetail.getAssetInfo());
            fluxDetail.setAmount(fundDetail.getAmount());
            fluxDetail.setFluxAction(FluxAction.REDUCE);
        } else {
            fluxDetail.setTargetAsset(fundDetail.getAssetInfo());
            fluxDetail.setAmount(fundDetail.getAmount());
            fluxDetail.setFluxAction(FluxAction.INCREASE);
        }
        fluxOrder.addDetail(fluxDetail);
    }

    /**
     * 填充待清算明细
     * @param fluxOrder
     * @param fundDetail
     */
    private void fillClearDetail(AssetFluxOrder fluxOrder, FundDetail fundDetail) {
        AssetFluxDetail fluxDetail = new AssetFluxDetail();
        fluxDetail.setAmount(fundDetail.getAmount());
    }
}
