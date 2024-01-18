package cn.doanything.payment.types;

import cn.doanything.commons.enums.BizIdType;
import cn.doanything.payment.types.PaymentType;

/**
 * @author wxj
 * 2024/1/15
 */
public enum IdType implements BizIdType {

    PAYMENT_INSTANT_ID("1", "01", "seq_payment_instant_id", "支付总单-直接支付模式"),
    PAY_ORDER_ID("2", "01", "seq_pay_order_id", "支付子单-支付单"),
    REFUND_ORDER_ID("2", "02", "seq_refund_order_id", "支付子单-退款单"),
    FUND_DETAIL_ID("3", "01", "seq_fund_detail_id", "支付明细单ID"),
    ;

    /**
     * 1位数字
     */
    private final String parentCode;

    /**
     * 2位数字
     */
    private final String code;

    private final String seqName;

    private final String name;

    IdType(String parentCode, String code, String seqName, String name) {
        this.parentCode = parentCode;
        this.code = code;
        this.seqName = seqName;
        this.name = name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getBizTypeCode() {
        return parentCode + code;
    }

    @Override
    public String getSeqName() {
        return seqName;
    }

}