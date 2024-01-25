package cn.doanything.payment.domain.flux.service;

import cn.doanything.payment.domain.PaymentConstants;
import cn.doanything.payment.domain.flux.AssetFluxOrder;
import cn.doanything.payment.domain.flux.BalanceFluxInstruct;
import cn.doanything.payment.domain.flux.ExternalFluxInstruct;
import cn.doanything.payment.domain.flux.FluxInstruct;
import cn.doanything.payment.types.asset.AssetInfo;
import cn.doanything.payment.types.asset.BalanceAsset;
import cn.doanything.payment.types.asset.BelongTo;
import cn.doanything.payment.types.funds.FluxAction;
import cn.doanything.payment.types.funds.FundDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxj
 * 2024/1/25
 */
public class FluxDomainService {

    public void execute(AssetFluxOrder fluxOrder, List<FundDetail> payerFundDetails, List<FundDetail> payeeFundDetails) {
        List<FluxInstruct> fluxInstructs = new ArrayList<>();
        payerFundDetails.forEach(fundDetail -> {
            AssetInfo assetInfo = fundDetail.getAssetInfo();
            FluxInstruct fluxInstruct;
            if (assetInfo instanceof BalanceAsset) {
                fluxInstruct = new BalanceFluxInstruct();
                fillInstructByBalance((BalanceFluxInstruct) fluxInstruct, (BalanceAsset) assetInfo, fundDetail.getBelongTo());
            } else {
                fluxInstruct = new ExternalFluxInstruct();
                fillInstructByExternal((ExternalFluxInstruct) fluxInstruct, assetInfo, fundDetail.getBelongTo());
            }
            fluxInstruct.setFluxOrderId(fluxOrder.getFluxOrderId());
            fluxInstruct.setAmount(fundDetail.getAmount());
            fluxInstruct.setFundDetailId(fundDetail.getDetailId());
            fluxInstructs.add(fluxInstruct);
        });

        payeeFundDetails.forEach(fundDetail -> {
            AssetInfo assetInfo = fundDetail.getAssetInfo();
            FluxInstruct fluxInstruct;
            if (assetInfo instanceof BalanceAsset) {
                fluxInstruct = new BalanceFluxInstruct();
                fillInstructByBalance((BalanceFluxInstruct) fluxInstruct, (BalanceAsset) assetInfo, fundDetail.getBelongTo());
            } else {
                fluxInstruct = new ExternalFluxInstruct();
            }
            fluxInstruct.setFluxOrderId(fluxOrder.getFluxOrderId());
            fluxInstruct.setAmount(fundDetail.getAmount());
            fluxInstruct.setFundDetailId(fundDetail.getDetailId());
            fluxInstructs.add(fluxInstruct);
        });
    }

    private void fillInstructByBalance(BalanceFluxInstruct fluxInstruct, BalanceAsset assetInfo, BelongTo belongTo) {
        if (belongTo == BelongTo.PAYER) {
            fluxInstruct.setDebitAsset(assetInfo);
            fluxInstruct.setCreditAsset(new BalanceAsset(PaymentConstants.INNER_MEMBER_ID, PaymentConstants.TRANSITION_ACCOUNT));
        } else {
            fluxInstruct.setCreditAsset(assetInfo);
            fluxInstruct.setDebitAsset(new BalanceAsset(PaymentConstants.INNER_MEMBER_ID, PaymentConstants.TRANSITION_ACCOUNT));
        }
    }

    private void fillInstructByExternal(ExternalFluxInstruct fluxInstruct, AssetInfo assetInfo, BelongTo belongTo) {
        fluxInstruct.setAssetInfo(assetInfo);
        fluxInstruct.setFluxAction(belongTo == BelongTo.PAYER ? FluxAction.REDUCE : FluxAction.INCREASE);
    }
}
