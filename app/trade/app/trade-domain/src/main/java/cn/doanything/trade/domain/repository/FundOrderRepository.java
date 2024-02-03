package cn.doanything.trade.domain.repository;

import cn.doanything.trade.domain.fund.FundOrder;

/**
 * @author wxj
 * 2024/2/3
 */
public interface FundOrderRepository {

    void store(FundOrder fundOrder);

    void reStore(FundOrder fundOrder);
}
