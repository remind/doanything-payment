package cn.doanything.trade.application.fund.impl;

import cn.doanything.trade.application.AbstractBaseTradeService;
import cn.doanything.trade.application.fund.FundService;
import cn.doanything.trade.domain.fund.FundOrder;
import cn.doanything.trade.domain.payment.PaymentOrder;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2024/2/3
 */
@Service
public class FundServiceImpl extends AbstractBaseTradeService implements FundService {

    @Override
    public PaymentOrder pay(FundOrder fundOrder) {
        PaymentOrder paymentOrder = initPaymentOrder(fundOrder);
        return null;
    }
}
