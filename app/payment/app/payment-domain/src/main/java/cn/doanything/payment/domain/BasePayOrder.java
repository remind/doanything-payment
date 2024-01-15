package cn.doanything.payment.domain;

import cn.doanything.commons.lang.types.Money;
import cn.doanything.payment.types.funds.FundDetail;
import cn.doanything.payment.types.funds.FundsRelation;
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
    private List<FundDetail> payeeDetails;

    /**
     * 付款方资金详情
     */
    private List<FundDetail> payerDetails;

    /**
     * 资金关系
     */
    private List<FundsRelation> fundsRelations;

}