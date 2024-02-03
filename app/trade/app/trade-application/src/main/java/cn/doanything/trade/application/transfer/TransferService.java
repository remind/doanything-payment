package cn.doanything.trade.application.transfer;

import cn.doanything.trade.application.AbstractBaseTradeService;
import cn.doanything.trade.application.TradeService;
import cn.doanything.trade.domain.fund.FundOrder;
import cn.doanything.trade.domain.payment.PaymentOrder;

/**
 * @author wxj
 * 2024/2/2
 */
public class TransferService extends AbstractBaseTradeService implements TradeService<FundOrder> {

    @Override
    public void create() {

    }

    @Override
    public void pay(FundOrder fundOrder) {
        // TODO
        /**
         * 1、调用支付
         * 2、根据支付结果调用订单状态机
         */
        PaymentOrder paymentOrder = new PaymentOrder();
        pay(paymentOrder);
    }
}
