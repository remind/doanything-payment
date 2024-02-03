package cn.doanything.trade.facade.fund;

import cn.doanything.trade.facade.fund.deposit.DepositRequest;
import cn.doanything.trade.facade.fund.deposit.DepositResponse;
import cn.doanything.trade.facade.fund.transfer.TransferRequest;
import cn.doanything.trade.facade.fund.transfer.TransferResponse;
import cn.doanything.trade.facade.fund.withdrawal.WithdrawalRequest;
import cn.doanything.trade.facade.fund.withdrawal.WithdrawalResponse;

/**
 * 资金类交易
 * @author wxj
 * 2024/2/2
 */
public interface FundTradeFacade {

    /**
     * 充值
     * 创建订单并提交到渠道
     * @param request
     * @return
     */
    DepositResponse deposit(DepositRequest request);

    /**
     * 转账
     * 余额到余额
     * @param request
     * @return
     */
    TransferResponse transfer(TransferRequest request);

    /**
     * 提现
     * 创建订单并提交到渠道
     * @param request
     * @return
     */
    WithdrawalResponse withdrawal(WithdrawalRequest request);

}
