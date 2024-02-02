package cn.doanything.trade.application;

import cn.doanything.trade.domain.TradeOrder;

/**
 * @author wxj
 * 2024/2/2
 */
public interface TradeService<T extends TradeOrder> {

    void create();

    void pay(T t);
}
