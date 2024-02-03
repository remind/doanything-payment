package cn.doanything.trade.application.fund;

import cn.doanything.commons.payment.result.PayResult;
import cn.doanything.trade.domain.fund.FundOrder;

/**
 * @author wxj
 * 2024/2/3
 */
public interface FundService {

    PayResult pay(FundOrder fundOrder);
}
