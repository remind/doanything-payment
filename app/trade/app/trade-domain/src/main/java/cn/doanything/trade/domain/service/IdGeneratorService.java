package cn.doanything.trade.domain.service;

import cn.doanything.trade.TradeType;

/**
 * @author wxj
 * 2024/2/3
 */
public interface IdGeneratorService {

    String genTradeId(String memberId, TradeType tradeType);

    String genPaymentOrderId(String tradeId);

}
