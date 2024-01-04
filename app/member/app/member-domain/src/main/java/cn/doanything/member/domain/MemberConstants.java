package cn.doanything.member.domain;

/**
 * 用户常量
 * @author wxj
 * 2024/1/2
 */
public interface MemberConstants {

    /**
     * 密码最大错误次数
     */
    int PWD_MAX_ERROR_COUNT = 3;

    /**
     * 默认的个人账户类型
     */
    String[] PERSONAL_DEFAULT_ACCOUNT_TYPE = {"101", "102"};
}
