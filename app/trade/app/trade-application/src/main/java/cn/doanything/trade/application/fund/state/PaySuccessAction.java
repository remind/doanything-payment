package cn.doanything.trade.application.fund.state;

import cn.doanything.framework.state.Action;
import cn.doanything.trade.application.TradeContext;
import cn.doanything.trade.application.TradeEvent;
import cn.doanything.types.status.AcquiringTradeStatus;

/**
 * @author wxj
 * 2024/2/20
 */
public class PaySuccessAction implements Action<AcquiringTradeStatus, TradeEvent, TradeContext> {

    @Override
    public void execute(AcquiringTradeStatus from, AcquiringTradeStatus to, TradeEvent event, TradeContext context) {

    }
}
