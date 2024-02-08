package cn.doanything.trade.facade.acquiring;

import cn.doanything.commons.payment.result.PayResult;
import cn.doanything.trade.facade.acquiring.instant.InstantRequest;
import cn.doanything.trade.facade.acquiring.instant.InstantResponse;

import java.util.List;

/**
 * 收单交易
 * @author wxj
 * 2024/2/7
 */
public interface AcquiringTradeFacade {

    /**
     * 创建即时交易
     * @param request
     * @return
     */
    InstantResponse createInstantTrade(InstantRequest request);

    /**
     * 支付
     * @param tradeIds
     * @return
     */
    PayResult pay(List<String> tradeIds);
}
