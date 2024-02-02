package cn.doanything.trade.application.transfer.state;

import cn.doanything.trade.application.TradeContext;
import cn.doanything.trade.application.TradeEvent;
import cn.doanything.trade.application.TradeStateMachineAction;
import cn.doanything.trade.status.TransferOrderStatus;

/**
 * @author wxj
 * 2024/2/2
 */
public class TransferFailAction extends TradeStateMachineAction<TransferOrderStatus> {
    @Override
    public void execute(TransferOrderStatus from, TransferOrderStatus to, TradeEvent event, TradeContext context) {

    }
}
