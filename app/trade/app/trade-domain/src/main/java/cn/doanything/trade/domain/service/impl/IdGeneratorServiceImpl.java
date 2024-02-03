package cn.doanything.trade.domain.service.impl;

import cn.doanything.commons.enums.BizIdType;
import cn.doanything.commons.enums.SystemCodeEnums;
import cn.doanything.framework.api.sequence.SequenceService;
import cn.doanything.trade.TradeType;
import cn.doanything.trade.domain.IdType;
import cn.doanything.trade.domain.service.IdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2024/2/3
 */
@Service
public class IdGeneratorServiceImpl implements IdGeneratorService {

    @Autowired
    private SequenceService sequenceService;


    @Override
    public String genTradeId(String memberId, TradeType tradeType) {
        return sequenceService.getId(memberId, SystemCodeEnums.TRADE, new BizIdType() {
            @Override
            public String getBizTypeCode() {
                return IdType.TRADE_ORDER_ID.getCode() + tradeType.getCode();
            }

            @Override
            public String getSeqName() {
                return IdType.TRADE_ORDER_ID.getSeqName();
            }
        });
    }

    @Override
    public String genPaymentOrderId(String tradeId) {
        return sequenceService.getIdByRouteId(tradeId, SystemCodeEnums.TRADE, new BizIdType() {
            @Override
            public String getBizTypeCode() {
                return IdType.PAYMENT_ORDER_ID.getCode() + "00";
            }

            @Override
            public String getSeqName() {
                return IdType.PAYMENT_ORDER_ID.getSeqName();
            }
        });
    }
}
