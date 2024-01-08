package cn.doanything.basic.mns.content;

import cn.doanything.commons.enums.EnableEnum;
import lombok.Data;

import java.util.Date;

/**
 * 短信验证码
 *
 * @author wxj
 * 2024/1/7
 */
@Data
public class AuthCode {

    /**
     * 验证码内容
     */
    private String authCode;

    /**
     * 消息文本
     */
    private String messageText;

    /**
     * 验证状态,可验证、失效
     */
    private EnableEnum authStatus;

    /**
     * 失效原因，超时、重复生成、验证通过
     */
    private String invalidReason;

    /**
     * 已验证次数
     */
    private Integer verifiedCount;

    /**
     * 可被验证次数
     */
    private Integer verifiableCount;

    /**
     * 有效分钟数
     */
    private int validMinute;

    /**
     * 过期时间
     */
    private Date expireTime;
}
