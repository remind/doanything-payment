package cn.doanything.payment.domain;

import cn.doanything.commons.lang.types.Money;
import cn.doanything.payment.types.funds.FundDetail;
import lombok.Data;

import java.util.List;


/**
 * @author wxj
 * 2024/1/15
 */
@Data
public abstract class BasePayOrder<T extends OrderStatus> {

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
    private Money orderAmount;

    /**
     * 发起人会员ID
     */
    private String memberId;

    /**
     * 订单状态
     */
    private OrderStatus orderStatus;

    /**
     * 收款方资金详情
     */
    private List<FundDetail> payeeDetails = List.of();

    /**
     * 付款方资金详情
     */
    private List<FundDetail> payerDetails = List.of();

    /**
     * 资金关系
     */
//    private List<FundsRelation> fundsRelations;

    public void addPayeeFundDetail(FundDetail fundDetail) {
        this.payeeDetails.add(fundDetail);
    }

    public void addPayerFundDetail(FundDetail fundDetail) {
        this.payeeDetails.add(fundDetail);
    }

}
