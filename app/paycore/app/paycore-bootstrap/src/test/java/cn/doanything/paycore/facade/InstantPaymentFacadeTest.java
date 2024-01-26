package cn.doanything.paycore.facade;

import cn.doanything.commons.lang.types.Money;
import cn.doanything.framework.BaseTestBootStarter;
import cn.doanything.paycore.facade.request.FundDetailInfo;
import cn.doanything.paycore.facade.request.InstantPaymentRequest;
import cn.doanything.paycore.facade.request.TradeInfo;
import cn.doanything.paycore.types.asset.BalanceAsset;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Currency;
import java.util.List;

/**
 * @author wxj
 * 2024/1/18
 */
public class InstantPaymentFacadeTest extends BaseTestBootStarter {

    @Autowired
    private InstantPaymentFacade instantPaymentFacade;

    @Test
    public void testPay() {
        InstantPaymentRequest request = new InstantPaymentRequest();
        request.setRequestId(getUUID());
        request.setMerchantId("123456");
        request.setPayAmount(new Money(1000, Currency.getInstance("CNY")));
        request.setPayerId("123456");

        FundDetailInfo fundDetailInfo = new FundDetailInfo();
        fundDetailInfo.setMemberId("123");
        fundDetailInfo.setAmount(new Money(1000, Currency.getInstance("CNY")));
        fundDetailInfo.setAssetInfo(new BalanceAsset("123", "1231"));
        request.setPayerFundDetail(List.of(fundDetailInfo));

        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setTradeAmount(new Money(1000, Currency.getInstance("CNY")));
        tradeInfo.setTradeId("tradeId123");
        tradeInfo.setPayeeId("123456");

        FundDetailInfo payeeFundDetail = new FundDetailInfo();
        payeeFundDetail.setMemberId("456");
        payeeFundDetail.setAmount(new Money(1000, Currency.getInstance("CNY")));
        payeeFundDetail.setAssetInfo(new BalanceAsset("456", "4561"));
        tradeInfo.setPayeeFundDetail(List.of(payeeFundDetail));
        request.setTradeInfos(List.of(tradeInfo));
        instantPaymentFacade.pay(request);
    }
}
