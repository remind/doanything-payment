package cn.doanything.trade.facade;

import cn.doanything.commons.payment.SettleTimeType;
import cn.doanything.commons.response.GlobalResultCode;
import cn.doanything.trade.domain.acquiring.AcquiringOrder;
import cn.doanything.trade.domain.acquiring.service.AcquiringDomainService;
import cn.doanything.trade.domain.repository.AcquiringTradeRepository;
import cn.doanything.trade.domain.service.IdGeneratorService;
import cn.doanything.trade.facade.acquiring.AcquiringTradeFacade;
import cn.doanything.trade.facade.acquiring.instant.InstantRequest;
import cn.doanything.trade.facade.acquiring.instant.InstantResponse;
import cn.doanything.types.TradeType;
import cn.doanything.types.status.AcquiringTradeStatus;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author wxj
 * 2024/2/7
 */
public class AcquiringTradeFacadeImpl implements AcquiringTradeFacade {

    @Autowired
    private IdGeneratorService idGeneratorService;

    @Autowired
    private AcquiringTradeRepository acquiringTradeRepository;

    @Autowired
    private AcquiringDomainService acquiringDomainService;

    @Override
    public InstantResponse createInstantTrade(InstantRequest request) {
        AcquiringOrder trade = build(request);
        acquiringTradeRepository.store(trade);
        InstantResponse response = new InstantResponse();
        response.setOutTradeNo(request.getOutTradeNo());
        response.setTradeId(trade.getTradeId());
        response.setStatus(AcquiringTradeStatus.WAIT_PAY);
        response.setResultCode(GlobalResultCode.SUCCESS.getCode());
        return response;
    }

    private AcquiringOrder build(InstantRequest request) {
        AcquiringOrder trade = new AcquiringOrder();
        trade.setTradeId(idGeneratorService.genTradeId(request.getBuyerId(), TradeType.INSTANT_ACQUIRING));
        trade.setOutTradeNo(request.getOutTradeNo());
        trade.setAmount(request.getAmount());
        trade.setProductCode(request.getProductCode());
        trade.setMemo(request.getMemo());
        trade.setGmtSubmit(LocalDateTime.now());
        trade.setPartnerId(request.getPartnerId());
        trade.setSellerId(request.getSellerId());
        trade.setBuyerId(request.getBuyerId());
        trade.setProductDesc(request.getProductDesc());
        trade.setShowUrl(request.getShowUrl());
        trade.setTradeType(TradeType.INSTANT_ACQUIRING);
        trade.setSettleTimeType(SettleTimeType.REAL);

        fillSellerAccount(trade, request);
        fillExpireTime(trade, request);
        return trade;
    }

    private void fillSellerAccount(AcquiringOrder trade, InstantRequest request) {
        if (StrUtil.isBlank(request.getSellerAccountNo())) {
            trade.setSellerAccountNo(acquiringDomainService.getSellerAccount(trade.getPartnerId(), trade.getSellerId()));
        } else {
            trade.setSellerAccountNo(request.getSellerAccountNo());
        }
    }

    private void fillExpireTime(AcquiringOrder trade, InstantRequest request) {
        if (request.getExpireTime() == null) {
            trade.setExpireTime(trade.getGmtSubmit().plusMinutes(30));
        } else {
            trade.setExpireTime(request.getExpireTime());
        }
    }

}
