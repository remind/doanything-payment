package cn.doanything.trade.application.fund;

import cn.doanything.trade.domain.fund.FundOrder;
import cn.doanything.trade.domain.payment.PaymentOrder;

/**
 * @author wxj
 * 2024/2/3
 */
public interface FundService {

    PaymentOrder pay(FundOrder fundOrder);
}
