package cn.doanything.trade.domain.rpc.paycore;

import cn.doanything.commons.payment.result.PayResult;
import cn.doanything.trade.domain.payment.PaymentOrder;

/**
 * @author wxj
 * 2024/2/3
 */
public interface PayCoreClient {

    PayResult pay(PaymentOrder paymentOrder);

}
