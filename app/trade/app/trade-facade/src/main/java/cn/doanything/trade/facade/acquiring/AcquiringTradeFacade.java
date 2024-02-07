package cn.doanything.trade.facade.acquiring;

import cn.doanything.trade.facade.acquiring.instant.InstantRequest;
import cn.doanything.trade.facade.acquiring.instant.InstantResponse;

/**
 * 收单交易
 * @author wxj
 * 2024/2/7
 */
public interface AcquiringTradeFacade {

    InstantResponse createInstantTrade(InstantRequest request);
}
