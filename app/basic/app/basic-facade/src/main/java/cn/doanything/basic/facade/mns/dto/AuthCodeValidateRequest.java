package cn.doanything.basic.facade.mns.dto;

import lombok.Data;

import java.util.Date;

/**
 * 校验验证码请求
 * @author wxj
 * 2024/1/7
 */
@Data
public class AuthCodeValidateRequest extends MessageRequest{


    /**
     * 发送时的请求ID
     */
    private String sendRequestId;

    /**
     * 验证时间
     */
    private Date authTime;

    /**
     * 验证码
     */
    private String authCode;
}
