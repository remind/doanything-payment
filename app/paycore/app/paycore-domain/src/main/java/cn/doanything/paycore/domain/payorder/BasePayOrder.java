package cn.doanything.paycore.domain.payorder;

import cn.doanything.commons.lang.Entity;
import cn.doanything.commons.lang.types.Money;
import cn.doanything.commons.lang.utils.AssertUtil;
import cn.doanything.paycore.types.OrderStatus;
import cn.doanything.paycore.types.asset.BelongTo;
import cn.doanything.paycore.types.funds.FundDetail;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * @author wxj
 * 2024/1/15
 */
@Data
public abstract class BasePayOrder<T extends OrderStatus> extends Entity {

    /**
     * 支付请求流水号
     */
    private String requestId;

    /**
     * 支付总单号
     */
    private String paymentId;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 订单金额
     */
    private Money amount;

    /**
     * 发起人会员ID
     */
    private String memberId;

    /**
     * 订单状态
     */
    private T orderStatus;

    /**
     * 订单扩展信息
     */
    private String orderExtension;

    /**
     * 收款方资金详情
     */
    private List<FundDetail> payeeDetails = new ArrayList<>();

    /**
     * 付款方资金详情
     */
    private List<FundDetail> payerDetails = new ArrayList<>();

    /**
     * 资金关系
     */
//    private List<FundsRelation> fundsRelations;

    public void addPayeeFundDetail(FundDetail fundDetail) {
        AssertUtil.isTrue(fundDetail.getBelongTo() == BelongTo.PAYEE, "收款方资金详情 belongTo 为 PAYEE");
        this.payeeDetails.add(fundDetail);
    }

    public void addPayerFundDetail(FundDetail fundDetail) {
        AssertUtil.isTrue(fundDetail.getBelongTo() == BelongTo.PAYER, "付款方资金详情 belongTo 为 PAYER");
        this.payerDetails.add(fundDetail);
    }

    public FundDetail getFundDetail(String fundDetailId) {
        FundDetail fundDetail = payeeDetails.stream().filter(fundDetail1 -> fundDetail1.getDetailId().equals(fundDetailId)).findFirst().orElse(null);
        return fundDetail != null ? fundDetail : payerDetails.stream().filter(fundDetail1 -> fundDetail1.getDetailId().equals(fundDetailId)).findFirst().orElse(null);
    }

}
