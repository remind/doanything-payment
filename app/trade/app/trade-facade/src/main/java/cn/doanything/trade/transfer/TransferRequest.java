package cn.doanything.trade.transfer;

import cn.doanything.commons.lang.types.Money;
import lombok.Data;

/**
 * @author wxj
 * 2024/2/2
 */
@Data
public class TransferRequest {

    /**
     * 付款人id
     */
    private String payerId;

    /**
     * 付款人账户号
     */
    private String payerAccountNo;

    /**
     * 收款人id
     */
    private String payeeId;

    /**
     * 收款人账户号
     */
    private String payeeAccountNo;

    /**
     * 转账金额
     */
    private Money amount;

    /**
     * 备注
     */
    private String memo;
}
