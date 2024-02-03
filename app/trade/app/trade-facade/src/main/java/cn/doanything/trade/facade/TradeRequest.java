package cn.doanything.trade.facade;

import cn.doanything.commons.lang.types.Money;
import cn.doanything.commons.terminal.TerminalInfo;
import lombok.Data;

/**
 * @author wxj
 * 2024/2/3
 */
@Data
public class TradeRequest {

    /**
     * 终端
     */
    private TerminalInfo terminalInfo;

    /**
     * 交易金额
     */
    private Money amount;

    /**
     * 备注
     */
    private String memo;
}
