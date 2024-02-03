package cn.doanything.trade.facade.fund.transfer;

import cn.doanything.trade.facade.TradeRequest;
import lombok.Data;

/**
 * @author wxj
 * 2024/2/2
 */
@Data
public class TransferRequest extends TradeRequest {

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

}
