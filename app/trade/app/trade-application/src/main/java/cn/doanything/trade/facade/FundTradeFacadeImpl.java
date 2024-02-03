package cn.doanything.trade.facade;

import cn.doanything.commons.payment.asset.BalanceAsset;
import cn.doanything.commons.payment.result.PayResult;
import cn.doanything.trade.TradeType;
import cn.doanything.trade.application.fund.FundService;
import cn.doanything.trade.domain.fund.FundOrder;
import cn.doanything.trade.domain.repository.FundOrderRepository;
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
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author wxj
 * 2024/2/2
 */
public class FundTradeFacadeImpl implements FundTradeFacade {

    @Autowired
    private FundService fundService;

    @Autowired
    private FundOrderRepository fundOrderRepository;

    @Autowired
    private IdGeneratorService idGeneratorService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public DepositResponse deposit(DepositRequest request) {
        FundOrder fundOrder = build(TradeType.DEPOSIT, request.getMemberId(), request);
        fundOrder.setPayerId(request.getMemberId());
        fundOrder.setPayerAsset(request.getDepositAsset());
        fundOrder.setPayeeId(request.getMemberId());
        fundOrder.setPayeeAsset(new BalanceAsset(request.getMemberId(), request.getAccountNo()));
        PayResult result = pay(fundOrder);
        DepositResponse response = new DepositResponse();
        response.setTradeId(fundOrder.getTradeId());
        response.setStatus(result.getPayStatus());
        response.setResultCode(result.getResultCode());
        response.setResultMessage(result.getResultMessage());
        return response;
    }

    @Override
    public TransferResponse transfer(TransferRequest request) {
        FundOrder fundOrder = build(TradeType.TRANSFER, request.getPayerId(), request);
        fundOrder.setPayeeId(request.getPayeeId());
        fundOrder.setPayeeAsset(new BalanceAsset(request.getPayeeId(), request.getPayeeAccountNo()));
        fundOrder.setPayerId(request.getPayerId());
        fundOrder.setPayerAsset(new BalanceAsset(request.getPayerId(), request.getPayerAccountNo()));
        PayResult result = pay(fundOrder);
        TransferResponse response = new TransferResponse();
        response.setTradeId(fundOrder.getTradeId());
        response.setStatus(result.getPayStatus());
        response.setResultCode(result.getResultCode());
        response.setResultMessage(result.getResultMessage());
        return response;
    }

    @Override
    public WithdrawalResponse withdrawal(WithdrawalRequest request) {
        FundOrder fundOrder = build(TradeType.WITHDRAWAL, request.getMemberId(), request);
        fundOrder.setPayerId(request.getMemberId());
        fundOrder.setPayerAsset(request.getBankCardAsset());
        fundOrder.setPayeeId(request.getMemberId());
        fundOrder.setPayeeAsset(new BalanceAsset(request.getMemberId(), request.getAccountNo()));
        PayResult result = pay(fundOrder);
        WithdrawalResponse response = new WithdrawalResponse();
        response.setTradeId(fundOrder.getTradeId());

        // TODO 提现状态

        response.setResultCode(result.getResultCode());
        response.setResultMessage(result.getResultMessage());
        return response;
    }

    private PayResult pay(FundOrder fundOrder) {
        return transactionTemplate.execute(status -> {
            fundOrderRepository.store(fundOrder);
            return fundService.pay(fundOrder);
        });
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
