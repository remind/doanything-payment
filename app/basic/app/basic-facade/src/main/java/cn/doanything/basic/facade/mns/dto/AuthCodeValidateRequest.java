package cn.doanything.basic.facade.mns.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author wxj
 * 2024/1/7
 */
@Data
public class AuthCodeValidateRequest {

    /**
     * 场景编码
     */
    private String sceneCode;

    /**
     * 收件方，手机号、邮箱
     */
    private String recipient;

    /**
     * 业务id，同一个业务ID下只能有一个处于可验证状态的
     */
    private String bizId;

    /**
     * 验证时间
     */
    private Date authTime;

    /**
     * 验证码
     */
    private String authCode;
}
