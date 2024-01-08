package cn.doanything.basic.domain.mns;

import cn.doanything.basic.mns.MessageStatus;
import cn.doanything.basic.mns.MessageType;
import cn.doanything.basic.mns.NotifyType;
import cn.doanything.basic.mns.Protocol;
import cn.doanything.commons.lang.Entity;
import lombok.Data;

import java.util.Date;

/**
 * @author wxj
 * 2024/1/6
 */
@Data
public class MessageDetail<T extends Object> extends Entity {

    private Long id;

    /**
     * 会员ID
     */
    private String memberId;

    /**
     * 请求id
     */
    private String requestId;

    /**
     * 批次ID
     * 验证码场景下：只有一条有效
     */
    private String batchId;

    /**
     * 场景编码
     */
    private String sceneCode;

    /**
     * 收件方，手机号、邮箱
     */
    private String recipient;

    /**
     * 协议，如短信、邮件、push
     */
    private Protocol protocol;

    /**
     * 消息类型，如普通消息、验证码
     */
    private MessageType messageType;

    /**
     * 通知类型，如实时、延时
     */
    private NotifyType notifyType;

    /**
     * 通知时间
     */
    private Date notifyTime;

    /**
     * 消息内容，其类型由protocol，messageType决定
     */
    private T content;

    /**
     * 状态,待发送、成功、失败
     */
    private MessageStatus status;

    /**
     * 备注
     */
    private String memo;
}
