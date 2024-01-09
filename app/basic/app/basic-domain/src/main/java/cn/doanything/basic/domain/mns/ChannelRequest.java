package cn.doanything.basic.domain.mns;

import cn.doanything.commons.enums.ResultStatusEnum;
import cn.doanything.commons.lang.Entity;
import lombok.Data;

/**
 * 渠道发送记录
 * @author wxj
 * 2024/1/7
 */
@Data
public class ChannelRequest extends Entity {
    /**
     * 消息ID
     */
    private String messageId;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 渠道返回id
     */
    private String responseId;

    /**
     * 渠道返回文本
     */
    private String responseText;

    /**
     * 状态,成功、失败
     */
    private ResultStatusEnum status;
}
