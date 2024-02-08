package cn.doanything.trade.domain.repository;

import cn.doanything.trade.domain.acquiring.AcquiringOrder;

/**
 * @author wxj
 * 2024/2/7
 */
public interface AcquiringTradeRepository {

    void store(AcquiringOrder acquiringOrder);

    AcquiringOrder load(String tradeId);
}
