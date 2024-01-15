package cn.doanything.payment.application.builder;

import cn.doanything.payment.domain.BasePayment;
import cn.doanything.payment.types.IdType;
import cn.doanything.payment.domain.service.IdGeneratorService;
import cn.doanything.payment.facade.request.BasePaymentRequest;
import cn.doanything.payment.facade.request.FundDetailInfo;
import cn.doanything.payment.types.PaymentType;
import cn.doanything.payment.types.asset.BelongTo;
import cn.doanything.payment.types.funds.FundDetail;
import cn.doanything.payment.types.funds.FundsAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author wxj
 * 2024/1/15
 */
public abstract class PaymentBuilder {

    @Autowired
    protected IdGeneratorService idGeneratorService;

    protected void fillPayment(BasePayment payment, BasePaymentRequest request, PaymentType paymentType) {
        payment.setPaymentId(idGeneratorService.genPaymentId(request.getMemberId(), paymentType.getIdType()));
        payment.setMemberId(request.getMemberId());
        payment.setPaymentType(paymentType);
    }

    protected List<FundDetail> buildFundDetails(String paymentId, String orderId, List<FundDetailInfo> info, BelongTo belongTo) {
        if (!CollectionUtils.isEmpty(info)) {
            return info.stream().map(i -> buildFundDetail(paymentId, orderId, i, belongTo)).toList();
        }
        return null;
    }

    protected FundDetail buildFundDetail(String paymentId, String orderId, FundDetailInfo info, BelongTo belongTo) {
        FundDetail fundDetail = new FundDetail();
        fundDetail.setDetailId(idGeneratorService.genSubPayOrder(paymentId, IdType.FUND_DETAIL_ID));
        fundDetail.setAmount(info.getAmount());
        fundDetail.setPartyId(info.getMemberId());
        fundDetail.setAssetInfo(info.getAssetInfo());
        fundDetail.setBelongTo(belongTo);
        fundDetail.setAction(belongTo == BelongTo.PAYER ? FundsAction.REDUCE : FundsAction.INCREASE);
        return fundDetail;
    }
}
