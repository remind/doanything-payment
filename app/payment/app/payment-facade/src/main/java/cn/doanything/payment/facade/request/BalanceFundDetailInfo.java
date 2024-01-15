package cn.doanything.payment.facade.request;

import lombok.Data;

/**
 * 余额资产明细
 * @author remind
 * 2023年07月14日 20:34
 */
@Data
public class BalanceFundDetailInfo extends FundDetailInfo {

    /**
     * 账户号
     */
    private String accountNo;
}
