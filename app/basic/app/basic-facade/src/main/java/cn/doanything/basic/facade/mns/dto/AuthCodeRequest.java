package cn.doanything.basic.facade.mns.dto;

import lombok.Data;

/**
 * 发送短信验证码请求
 * @author wxj
 * 2024/1/7
 */
@Data
public class AuthCodeRequest extends MessageRequest {

    /**
     * 业务id，同一个业务ID下只能有一个处于可验证状态的
     */
    private String bizId;

}
