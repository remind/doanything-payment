package cn.doanything.paycore.domain.flux.service;

import cn.doanything.paycore.domain.PaymentConstants;
import cn.doanything.paycore.domain.flux.*;
import cn.doanything.paycore.types.asset.AssetInfo;
import cn.doanything.paycore.types.asset.BalanceAsset;
import cn.doanything.paycore.types.asset.BelongTo;
import cn.doanything.paycore.types.funds.FundDetail;

import java.util.List;

/**
 * @author wxj
 * 2024/1/25
 */
public class FluxDomainService {

    public void pay(AssetFluxOrder fluxOrder, List<FundDetail> payerFundDetails, List<FundDetail> payeeFundDetails) {
        payerFundDetails.forEach(fundDetail -> {
            AssetInfo assetInfo = fundDetail.getAssetInfo();
            AssetFluxInstruct assetFluxInstruct;
            if (assetInfo instanceof BalanceAsset) {
                assetFluxInstruct = new BalanceAssetFluxInstruct();
                fillInstructByBalance((BalanceAssetFluxInstruct) assetFluxInstruct, (BalanceAsset) assetInfo, fundDetail);
            } else {
                assetFluxInstruct = new ExternalAssetFluxInstruct();
                fillInstructByExternal((ExternalAssetFluxInstruct) assetFluxInstruct, assetInfo, fundDetail);
            }
            assetFluxInstruct.setInstructType(InstructType.FORWARD);
            assetFluxInstruct.setFluxOrderId(fluxOrder.getFluxOrderId());
            assetFluxInstruct.setAmount(fundDetail.getAmount());
            assetFluxInstruct.setFundDetailId(fundDetail.getDetailId());
            fluxOrder.addFluxInstruct(assetFluxInstruct);
        });

        payeeFundDetails.forEach(fundDetail -> {
            AssetInfo assetInfo = fundDetail.getAssetInfo();
            AssetFluxInstruct assetFluxInstruct;
            if (assetInfo instanceof BalanceAsset) {
                assetFluxInstruct = new BalanceAssetFluxInstruct();
                fillInstructByBalance((BalanceAssetFluxInstruct) assetFluxInstruct, (BalanceAsset) assetInfo, fundDetail);
            } else {
                assetFluxInstruct = new ExternalAssetFluxInstruct();
                fillInstructByExternal((ExternalAssetFluxInstruct) assetFluxInstruct, assetInfo, fundDetail);
            }
            assetFluxInstruct.setInstructType(InstructType.FORWARD);
            assetFluxInstruct.setFluxOrderId(fluxOrder.getFluxOrderId());
            assetFluxInstruct.setAmount(fundDetail.getAmount());
            assetFluxInstruct.setFundDetailId(fundDetail.getDetailId());
            fluxOrder.addFluxInstruct(assetFluxInstruct);
        });
    }

    private void fillInstructByBalance(BalanceAssetFluxInstruct fluxInstruct, BalanceAsset assetInfo, FundDetail fundDetail) {
        if (fundDetail.getBelongTo() == BelongTo.PAYER) {
            fluxInstruct.setDebitAsset(assetInfo);
            fluxInstruct.setCreditAsset(new BalanceAsset(PaymentConstants.INNER_MEMBER_ID, PaymentConstants.TRANSITION_ACCOUNT));
        } else {
            fluxInstruct.setCreditAsset(assetInfo);
            fluxInstruct.setDebitAsset(new BalanceAsset(PaymentConstants.INNER_MEMBER_ID, PaymentConstants.TRANSITION_ACCOUNT));
        }
    }

    private void fillInstructByExternal(ExternalAssetFluxInstruct fluxInstruct, AssetInfo assetInfo, FundDetail fundDetail) {
        fluxInstruct.setAssetInfo(assetInfo);
        fluxInstruct.setFundAction(fundDetail.getFundAction());
    }
}
