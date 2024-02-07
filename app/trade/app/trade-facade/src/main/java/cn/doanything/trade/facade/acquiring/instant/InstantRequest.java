package cn.doanything.trade.facade.acquiring.instant;

import cn.doanything.trade.facade.TradeRequest;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 即时到账请求
 * @author wxj
 * 2024/2/7
 */
@Data
public class InstantRequest extends TradeRequest {

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
     * 买家ID
     */
    private String buyerId;

    /**
     * 产品码
     */
    private String productCode;

    /**
     * 产品描述信息
     */
    private String productDesc;

    /**
     * 产品展现 URL
     */
    private String showUrl;

    /**
     * 过期时间，为空则默认30分钟后过期
     */
    private LocalDateTime expireTime;

}
