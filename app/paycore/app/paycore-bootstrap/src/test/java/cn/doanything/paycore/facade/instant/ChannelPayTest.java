package cn.doanything.paycore.facade.instant;

import cn.doanything.commons.lang.types.Money;
import cn.doanything.framework.BaseTestBootStarter;
import cn.doanything.paycore.facade.InstantPaymentFacade;
import cn.doanything.paycore.facade.request.FundDetailInfo;
import cn.doanything.paycore.facade.request.InstantPaymentRequest;
import cn.doanything.paycore.facade.request.TradeInfo;
import cn.doanything.paycore.types.asset.BalanceAsset;
import cn.doanything.paycore.types.asset.BankCardAsset;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Currency;
import java.util.List;

/**
 * @author wxj
 * 2024/1/29
 */
public class ChannelPayTest extends BaseTestBootStarter {
    @Autowired
    private InstantPaymentFacade instantPaymentFacade;

    @Test
    public void testPay() {
        InstantPaymentRequest request = new InstantPaymentRequest();
        request.setRequestId(getUUID());
        request.setMerchantId("merchantId123");
        request.setPayAmount(new Money(2000, Currency.getInstance("CNY")));
        request.setPayerId("payer123");

        request.setPayerFundDetail(getPayerFundDetail());

        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setTradeAmount(new Money(2000, Currency.getInstance("CNY")));
        tradeInfo.setTradeId("tradeId123");
        tradeInfo.setPayeeId("payee123");

        FundDetailInfo payeeFundDetail = new FundDetailInfo();
        payeeFundDetail.setMemberId("payee123");
        payeeFundDetail.setAmount(new Money(2000, Currency.getInstance("CNY")));
        payeeFundDetail.setAssetInfo(new BalanceAsset("payee123", "payee-account"));
        tradeInfo.setPayeeFundDetail(List.of(payeeFundDetail));
        request.setTradeInfos(List.of(tradeInfo));
        instantPaymentFacade.pay(request);
    }

    private List<FundDetailInfo> getPayerFundDetail() {
        FundDetailInfo fundDetailInfo1 = new FundDetailInfo();
        fundDetailInfo1.setMemberId("payer123");
        fundDetailInfo1.setAmount(new Money(1000, Currency.getInstance("CNY")));
        fundDetailInfo1.setAssetInfo(new BankCardAsset("bankCardNo123"));

        FundDetailInfo fundDetailInfo2 = new FundDetailInfo();
        fundDetailInfo2.setMemberId("payer123");
        fundDetailInfo2.setAmount(new Money(1000, Currency.getInstance("CNY")));
        fundDetailInfo2.setAssetInfo(new BankCardAsset("bankCardNo456"));
        return List.of(fundDetailInfo1, fundDetailInfo2);
    }
}
