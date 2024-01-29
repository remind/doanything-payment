package cn.doanything.paycore.domain.payorder.service;

import cn.doanything.paycore.domain.asset.factory.AssetFluxFactory;
import cn.doanything.paycore.domain.flux.*;
import cn.doanything.paycore.domain.flux.chain.InstructChainService;
import cn.doanything.paycore.domain.payorder.BasePayOrder;
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

    @Autowired
    protected InstructChainService instructChainService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    protected FluxOrder buildFluxOrder(BasePayOrder payOrder) {
        FluxOrder fluxOrder = new FluxOrder();
        fluxOrder.setPaymentId(payOrder.getPaymentId());
        fluxOrder.setPayId(payOrder.getOrderId());
        fluxOrder.setFluxOrderId(idGeneratorService.genIdByRelateId(payOrder.getPaymentId(), IdType.FLUX_ORDER_ID));
        fluxOrder.setStatus(FluxOrderStatus.INIT);
        fillFluxInstruct(fluxOrder, payOrder.getPayerDetails(), payOrder.getPayeeDetails());
        return fluxOrder;
    }

    protected void fillFluxInstruct(FluxOrder fluxOrder, List<FundDetail> payerFundDetails, List<FundDetail> payeeFundDetails) {
        payerFundDetails.forEach(fundDetail -> {
            fillFluxInstruction(fluxOrder, fundDetail);
        });

        payeeFundDetails.forEach(fundDetail -> {
            fillFluxInstruction(fluxOrder, fundDetail);
        });
    }

    private void fillFluxInstruction(FluxOrder fluxOrder, FundDetail fundDetail) {
        AssetInfo assetInfo = fundDetail.getAssetInfo();
        FluxInstruction fluxInstruction = assetFluxFactory.getFluxInstructBuilder(assetInfo.getAssetType()).build(fundDetail);
        fluxInstruction.setInstructionId(idGeneratorService.genIdByRelateId(fluxOrder.getFluxOrderId(), IdType.FLUX_INSTRUCT_ID));
        fluxInstruction.setPaymentId(fundDetail.getPaymentId());
        fluxInstruction.setPayId(fundDetail.getOrderId());
        fluxInstruction.setStatus(InstructStatus.INIT);
        fluxInstruction.setType(InstructionType.FORWARD);
        fluxInstruction.setFluxOrderId(fluxOrder.getFluxOrderId());
        fluxInstruction.setAmount(fundDetail.getAmount());
        fluxInstruction.setFundDetailId(fundDetail.getDetailId());
        instructChainService.addInstruct(fluxOrder, fluxInstruction);
    }
}