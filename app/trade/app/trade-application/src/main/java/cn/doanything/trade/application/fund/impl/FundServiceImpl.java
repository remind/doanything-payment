package cn.doanything.trade.application.fund.impl;

import cn.doanything.commons.payment.asset.BelongTo;
import cn.doanything.commons.payment.result.PayResult;
import cn.doanything.trade.application.AbstractBaseTradeService;
import cn.doanything.trade.application.fund.FundService;
import cn.doanything.trade.domain.fund.FundOrder;
import cn.doanything.trade.domain.payment.PaymentOrder;
import cn.doanything.trade.domain.payment.PaymentParty;
import cn.doanything.trade.domain.repository.PaymentOrderRepository;
import cn.doanything.trade.domain.rpc.paycore.PayCoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2024/2/3
 */
@Service
public class FundServiceImpl extends AbstractBaseTradeService implements FundService {

    @Autowired
    private PayCoreClient payCoreClient;

    private PaymentOrderRepository paymentOrderRepository;

    @Override
    public PayResult pay(FundOrder fundOrder) {
        PaymentOrder paymentOrder = initPaymentOrder(fundOrder);
        fillPaymentFromFundOrder(paymentOrder, fundOrder);
        paymentOrderRepository.store(paymentOrder);
        PayResult payResult = payCoreClient.pay(paymentOrder);

        // TODO 调用状态机处理
        return payResult;
    }

    private void fillPaymentFromFundOrder(PaymentOrder paymentOrder, FundOrder fundOrder) {
        paymentOrder.setPayeeId(fundOrder.getPayeeId());

        PaymentParty payerParty = new PaymentParty();
        payerParty.setAmount(fundOrder.getAmount());
        payerParty.setBelongTo(BelongTo.PAYER);
        payerParty.setAssetInfo(fundOrder.getPayerAsset());
        payerParty.setMemberId(fundOrder.getPayerId());
        paymentOrder.getPayerParty().add(payerParty);

        PaymentParty payeeParty = new PaymentParty();
        payeeParty.setAmount(fundOrder.getAmount());
        payeeParty.setBelongTo(BelongTo.PAYEE);
        payeeParty.setAssetInfo(fundOrder.getPayeeAsset());
        payeeParty.setMemberId(fundOrder.getPayeeId());
        paymentOrder.getPayeeParty().add(payeeParty);
    }
}
