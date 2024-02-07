package cn.doanything.trade.config;

import cn.doanything.framework.state.builder.SateMachineBuilder;
import cn.doanything.types.TradeType;
import cn.doanything.trade.application.TradeEvent;
import cn.doanything.types.status.TransferOrderStatus;
import org.springframework.context.annotation.Configuration;

/**
 * @author wxj
 * 2024/2/2
 */
@Configuration
public class SateMachineConfigure {

    public void configure() {
        SateMachineBuilder.create(TradeType.TRANSFER.getCode())
                .source(TransferOrderStatus.INIT)
                .event(TradeEvent.START_PAY)
                .target(TransferOrderStatus.PAYING)
                .action((from, to, event, context) -> {
                    System.out.println("trade success");
                })
                .and()
                .source("init")
                .event("cancel")
                .target("fail")
                .action((from, to, event, context) -> {
                    System.out.println("trade fail");
                }).build();
    }
}
