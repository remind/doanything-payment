package cn.doanything.paycore.application.builder;

import cn.doanything.paycore.domain.BasePayment;
import cn.doanything.paycore.domain.service.IdGeneratorDomainService;
import cn.doanything.paycore.facade.request.BasePaymentRequest;
import cn.doanything.paycore.facade.request.FundDetailInfo;
import cn.doanything.paycore.types.IdType;
import cn.doanything.paycore.types.PaymentType;
import cn.doanything.paycore.types.asset.BelongTo;
import cn.doanything.paycore.types.funds.FundAction;
import cn.doanything.paycore.types.funds.FundDetail;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wxj
 * 2024/1/15
 */
public abstract class PaymentBuilder {

    @Autowired
    protected IdGeneratorDomainService idGeneratorDomainService;

    /**
     * 填充支付基础信息
     * @param payment
     * @param request
     * @param paymentType
     */
    protected void fillBasePayment(BasePayment payment, BasePaymentRequest request, PaymentType paymentType) {
        payment.setPaymentId(idGeneratorDomainService.genPaymentId(request.getMemberId(), paymentType.getIdType()));
        payment.setMemberId(request.getMemberId());
        payment.setPaymentType(paymentType);
        payment.setMerchantId(request.getMerchantId());
    }

    /**
     * 构造资金明细
     * @param paymentId
     * @param orderId
     * @param info
     * @param belongTo
     * @return
     */
    protected FundDetail buildFundDetail(String paymentId, String orderId, FundDetailInfo info, BelongTo belongTo) {
        FundDetail fundDetail = new FundDetail();
        fundDetail.setPaymentId(paymentId);
        fundDetail.setOrderId(orderId);
        fundDetail.setDetailId(idGeneratorDomainService.genIdByRelateId(paymentId, IdType.FUND_DETAIL_ID));
        fundDetail.setAmount(info.getAmount());
        fundDetail.setMemberId(info.getMemberId());
        fundDetail.setAssetInfo(info.getAssetInfo());
        fundDetail.setBelongTo(belongTo);
        fundDetail.setFundAction(belongTo == BelongTo.PAYER ? FundAction.REDUCE : FundAction.INCREASE);
        return fundDetail;
    }
}
