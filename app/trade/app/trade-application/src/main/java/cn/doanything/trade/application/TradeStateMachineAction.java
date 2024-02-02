package cn.doanything.trade.application;

import cn.doanything.framework.state.Action;
import cn.doanything.trade.status.TradeOrderStatus;

/**
 * @author wxj
 * 2024/2/2
 */
public abstract class TradeStateMachineAction<S extends TradeOrderStatus> implements Action<S, TradeEvent, TradeContext> {

}
