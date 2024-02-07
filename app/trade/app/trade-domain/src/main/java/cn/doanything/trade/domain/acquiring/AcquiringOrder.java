package cn.doanything.trade.domain.acquiring;

import cn.doanything.commons.payment.SettleTimeType;
import cn.doanything.trade.domain.TradeOrder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 收单订单
 * @author wxj
 * 2024/1/31
 */
@Data
public class AcquiringOrder extends TradeOrder {

    /**
     * 外部业务号
     */
    private String outTradeNo;

    /**
     * 平台方ID
     */
    private String partnerId;

    /**
     * 卖家ID
     */
    private String sellerId;

    /**
     * 卖家账户
     */
    private String sellerAccountNo;

    /**
     * 买家ID
     */
    private String buyerId;

    /**
     * 产品描述信息
     */
    private String productDesc;

    /**
     * 产品展现 URL
     */
    private String showUrl;

    /**
     * 结算时间类型
     */
    private SettleTimeType settleTimeType;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;
}
