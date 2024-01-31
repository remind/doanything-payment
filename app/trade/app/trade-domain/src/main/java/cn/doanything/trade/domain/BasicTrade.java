package cn.doanything.trade.domain;

import cn.doanything.commons.lang.types.Money;
import cn.doanything.trade.TradeType;
import lombok.Data;

/**
 * @author wxj
 * 2024/1/31
 */
@Data
public class BasicTrade {

    private String tradeId;

    private String bizNo;

    private TradeType type;

    private Money amount;

}
