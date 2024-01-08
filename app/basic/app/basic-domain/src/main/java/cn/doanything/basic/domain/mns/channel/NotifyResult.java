package cn.doanything.basic.domain.mns.channel;

import cn.doanything.commons.enums.ResultStatusEnum;
import lombok.Data;

/**
 * 发送结果
 * @author wxj
 * 2024/1/7
 */
@Data
public class NotifyResult {

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
