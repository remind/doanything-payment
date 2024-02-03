package cn.doanything.trade.facade;

import cn.doanything.commons.payment.asset.BalanceAsset;
import cn.doanything.trade.TradeType;
import cn.doanything.trade.application.transfer.TransferService;
import cn.doanything.trade.domain.fund.FundOrder;
import cn.doanything.trade.domain.service.IdGeneratorService;
import cn.doanything.trade.facade.fund.FundTradeFacade;
import cn.doanything.trade.facade.fund.deposit.DepositRequest;
import cn.doanything.trade.facade.fund.deposit.DepositResponse;
import cn.doanything.trade.facade.fund.transfer.TransferRequest;
import cn.doanything.trade.facade.fund.transfer.TransferResponse;
import cn.doanything.trade.facade.fund.withdrawal.WithdrawalRequest;
import cn.doanything.trade.facade.fund.withdrawal.WithdrawalResponse;
import cn.doanything.trade.status.FundOrderStatus;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wxj
 * 2024/2/2
 */
public class FundTradeFacadeImpl implements FundTradeFacade {

    @Autowired
    private TransferService transferService;

    @Autowired
    private IdGeneratorService idGeneratorService;

    @Override
    public DepositResponse deposit(DepositRequest request) {
        FundOrder fundOrder = build(TradeType.DEPOSIT, request.getMemberId(), request);
        fundOrder.setPayerId(request.getMemberId());
        fundOrder.setPayerAsset(request.getDepositAsset());
        fundOrder.setPayeeId(request.getMemberId());
        fundOrder.setPayeeAsset(new BalanceAsset(request.getMemberId(), request.getAccountNo()));
        return null;
    }

    @Override
    public TransferResponse transfer(TransferRequest request) {
        FundOrder fundOrder = build(TradeType.TRANSFER, request.getPayerId(), request);
        fundOrder.setPayeeId(request.getPayeeId());
        fundOrder.setPayeeAsset(new BalanceAsset(request.getPayeeId(), request.getPayeeAccountNo()));
        fundOrder.setPayerId(request.getPayerId());
        fundOrder.setPayerAsset(new BalanceAsset(request.getPayerId(), request.getPayerAccountNo()));
        transferService.pay(fundOrder);
        return null;
    }

    @Override
    public WithdrawalResponse withdrawal(WithdrawalRequest request) {
        FundOrder fundOrder = build(TradeType.WITHDRAWAL, request.getMemberId(), request);
        fundOrder.setPayerId(request.getMemberId());
        fundOrder.setPayerAsset(request.getBankCardAsset());
        fundOrder.setPayeeId(request.getMemberId());
        fundOrder.setPayeeAsset(new BalanceAsset(request.getMemberId(), request.getAccountNo()));
        return null;
    }

    private FundOrder build(TradeType tradeType, String memberId, TradeRequest request) {
        FundOrder fundOrder = new FundOrder();
        fundOrder.setTradeId(idGeneratorService.genTradeId(memberId, tradeType));
        fundOrder.setTradeType(tradeType);
        fundOrder.setStatus(FundOrderStatus.INIT);
        fundOrder.setAmount(request.getAmount());
        fundOrder.setMemo(request.getMemo());
        return fundOrder;
    }
}
