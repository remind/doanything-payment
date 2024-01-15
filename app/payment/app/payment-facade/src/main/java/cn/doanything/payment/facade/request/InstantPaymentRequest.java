package cn.doanything.payment.facade.request;

import cn.doanything.commons.lang.types.Money;
import lombok.Data;

import java.util.List;

/**
 * 支付请求
 * @author remind
 * 2023年07月14日 20:28
 */
@Data
public class InstantPaymentRequest extends BasePaymentRequest {

    /**
     * 支付金额
     */
    private Money amount;

    /**
     * 收款资金明细
     */
    private List<FundDetailInfo> payeeFundDetail;

    /**
     * 付款资金明细
     */
    private List<FundDetailInfo> payerFundDetail;
}
