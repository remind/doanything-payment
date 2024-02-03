package cn.doanything.trade.transfer;

import cn.doanything.trade.status.TransferOrderStatus;
import lombok.Data;

/**
 * @author wxj
 * 2024/2/2
 */
@Data
public class TransferResponse {

    /**
     * 转账流水号
     */
    private String tradeId;

    /**
     * 状态
     */
    private TransferOrderStatus status;

    /**
     * 消息
     */
    private String message;
}
