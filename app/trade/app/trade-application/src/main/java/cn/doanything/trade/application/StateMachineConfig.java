package cn.doanything.trade.application;

import cn.doanything.framework.state.StateMachineFactory;
import cn.doanything.framework.state.builder.SateMachineBuilder;
import cn.doanything.trade.application.fund.state.PaySuccessAction;
import cn.doanything.types.status.AcquiringTradeStatus;

/**
 * @author wxj
 * 2024/2/20
 */
public class StateMachineConfig {

    public void initFundStateMachine() {
        SateMachineBuilder<AcquiringTradeStatus, TradeEvent, TradeContext> builder = SateMachineBuilder.create("fundStateMachine");
        builder.source(AcquiringTradeStatus.WAIT_PAY)
                .event(TradeEvent.PAY_SUCCESS)
                .action(new PaySuccessAction())
                .target(AcquiringTradeStatus.PAY_SUCCESS);
        StateMachineFactory.register(builder.build());
        StateMachineFactory.get("fundStateMachine").fireEvent(AcquiringTradeStatus.WAIT_PAY, TradeEvent.PAY_SUCCESS, null);
    }
}
