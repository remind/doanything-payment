package cn.doanything.trade.facade.fund.withdrawal;

import cn.doanything.commons.payment.asset.BankCardAsset;
import cn.doanything.trade.facade.TradeRequest;
import lombok.Data;

/**
 * 提现请求
 * @author wxj
 * 2024/2/3
 */
@Data
public class WithdrawalRequest extends TradeRequest {

    /**
     * 会员ID
     */
    private String memberId;

    /**
     * 账户号
     */
    private String accountNo;

    /**
     * 到账银行卡
     */
    private BankCardAsset bankCardAsset;

}
