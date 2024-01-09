package cn.doanything.basic.facade.mns.dto;

import lombok.Data;

/**
 * 消息请求
 * @author wxj
 * 2024/1/7
 */
@Data
public class MessageRequest {

    /**
     * 场景编码
     */
    private String sceneCode;

    /**
     * 收件方，手机号、邮箱
     */
    private String recipient;

    /**
     * 会员ID，可空
     */
    private String memberId;
}
