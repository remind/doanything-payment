package cn.doanything.payment.facade.request;

import lombok.Data;

/**
 * 银行卡资产信息
 * @author remind
 * 2023年07月14日 20:34
 */
@Data
public class BankCardFundDetailInfo extends FundDetailInfo {

    /**
     * 银行卡ID
     */
    private String bankCardId;

    /**
     * 行号
     */
    private String bankCode;

    /**
     * 银行卡号
     */
    private String bankCardNo;

}
