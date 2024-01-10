package cn.doanything.basic.domain;

/**
 * @author wxj
 * 2024/1/7
 */
public interface BasicConstants {

    /**
     * 验证码长度
     */
    int MNS_AUTH_CODE_LENGTH = 6;

    /**
     * 验证码有效时间
     */
    int MNS_AUTH_CODE_VALID_MINUTE = 15;

    /**
     * 验证码可验证次数
     */
    int MNS_AUTH_CODE_VERIFIABLE_COUNT = 3;

    /**
     * 默认任务类型
     */
    String DEFAULT_TASK_TYPE = "notify_message";
}
