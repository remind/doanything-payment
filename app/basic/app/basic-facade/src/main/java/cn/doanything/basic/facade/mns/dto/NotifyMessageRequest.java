package cn.doanything.basic.facade.mns.dto;

import cn.doanything.basic.mns.Protocol;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * 通知消息
 * @author wxj
 * 2024/1/9
 */
@Data
public class NotifyMessageRequest extends MessageRequest {

    /**
     * 协议
     */
    private Protocol protocol;

    /**
     * 是否实时
     */
    private boolean real;

    /**
     * 发送时间
     */
    private Date notifyTime;

    /**
     * 邮件标题
     */
    private String subject;

    /**
     * 参数
     */
    private Map<String, Object> param;

}
