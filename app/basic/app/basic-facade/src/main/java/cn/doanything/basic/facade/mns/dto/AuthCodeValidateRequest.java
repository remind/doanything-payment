package cn.doanything.basic.facade.mns.dto;

import lombok.Data;

/**
 * 校验验证码请求
 *
 * @author wxj
 * 2024/1/7
 */
@Data
public class AuthCodeValidateRequest {


    /**
     * 发送时的请求ID
     */
    private String sendRequestId;

    /**
     * 场景编码
     */
    private String sceneCode;

    /**
     * 收件方，手机号、邮箱
     */
    private String recipient;

    /**
     * 验证码
     */
    private String authCode;
}
