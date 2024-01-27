package cn.doanything.paycore.domain.payorder.service;

import cn.doanything.paycore.domain.payorder.BasePayOrder;
import cn.doanything.paycore.domain.asset.AssetFluxFactory;
import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.domain.flux.InstructionType;
import cn.doanything.paycore.domain.service.IdGeneratorService;
import cn.doanything.paycore.types.IdType;
import cn.doanything.paycore.types.asset.AssetInfo;
import cn.doanything.paycore.types.funds.FundDetail;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wxj
 * 2024/1/27
 */
public abstract class AbstractBasePayService {

    @Autowired
    protected IdGeneratorService idGeneratorService;

    @Autowired
    protected AssetFluxFactory assetFluxFactory;

    @SuppressWarnings({"unchecked", "rawtypes"})
    protected FluxOrder buildFluxOrder(BasePayOrder payOrder) {
        FluxOrder fluxOrder = new FluxOrder();
        fluxOrder.setPaymentId(payOrder.getPaymentId());
        fluxOrder.setOrderId(payOrder.getOrderId());
        fluxOrder.setFluxOrderId(idGeneratorService.genIdByRelateId(payOrder.getPaymentId(), IdType.FLUX_ORDER_ID));
        fillFluxInstruct(fluxOrder, payOrder.getPayerDetails(), payOrder.getPayeeDetails());
        return fluxOrder;
    }

    protected void fillFluxInstruct(FluxOrder fluxOrder, List<FundDetail> payerFundDetails, List<FundDetail> payeeFundDetails) {
        payerFundDetails.forEach(fundDetail -> {
            AssetInfo assetInfo = fundDetail.getAssetInfo();
            FluxInstruction fluxInstruction = assetFluxFactory.getFluxInstructBuilder(assetInfo.getAssetType()).build(fundDetail);
            fluxInstruction.setInstructionType(InstructionType.FORWARD);
            fluxInstruction.setFluxOrderId(fluxOrder.getFluxOrderId());
            fluxInstruction.setAmount(fundDetail.getAmount());
            fluxInstruction.setFundDetailId(fundDetail.getDetailId());
            fluxOrder.addFluxInstruct(fluxInstruction);
        });

        payeeFundDetails.forEach(fundDetail -> {
            AssetInfo assetInfo = fundDetail.getAssetInfo();
            FluxInstruction fluxInstruction = assetFluxFactory.getFluxInstructBuilder(assetInfo.getAssetType()).build(fundDetail);
            fluxInstruction.setInstructionType(InstructionType.FORWARD);
            fluxInstruction.setFluxOrderId(fluxOrder.getFluxOrderId());
            fluxInstruction.setAmount(fundDetail.getAmount());
            fluxInstruction.setFundDetailId(fundDetail.getDetailId());
            fluxOrder.addFluxInstruct(fluxInstruction);
        });
    }
}