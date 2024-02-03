package cn.doanything.trade.facade;

import lombok.Data;

/**
 * @author wxj
 * 2024/2/3
 */
@Data
public class TradeResponse {

    /**
     * 交易ID
     */
    private String tradeId;

    /**
     * 返回结果码
     */
    private String resultCode;

    /**
     * 返回信息
     */
    private String resultMessage;
}
