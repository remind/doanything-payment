package cn.doanything.basic.facade.mns.dto;

import lombok.Data;

/**
 * 发送短信验证码请求
 * @author wxj
 * 2024/1/7
 */
@Data
public class AuthCodeMessageRequest extends MessageRequest {

    /**
     * 批次ID，该批次内的验证码只能有一条有效的
     */
    private String batchId;

}
