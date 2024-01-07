package cn.doanything.basic.domain.mns;

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
public class MessageAuthCode extends MessageDetail {

    /**
     * 业务id，同一个业务ID下只能有一个处于可验证状态的
     */
    private String bizId;

    /**
     * 验证码内容
     */
    private String authCode;

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
