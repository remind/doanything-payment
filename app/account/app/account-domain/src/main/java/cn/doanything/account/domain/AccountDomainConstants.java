package cn.doanything.account.domain;

/**
 * @author wxj
 * 2023/12/16
 */
public interface AccountDomainConstants {

    /**
     * 科目层级最小值
     */
    int ACCOUNT_TITLE_LEVEL_MIN = 1;

    /**
     * 科目层级最大值
     */
    int ACCOUNT_TITLE_LEVEL_MAX = 3;

    /**
     * 默认资金类型
     */
    String DEFAULT_FUND_TYPE = "NORMAL";

    /**
     * 外部户账户自增最大自增值
     */
    int OUTER_ACCOUNT_NO_MAX_INC = 99999;

    /**
     * 内部户账户自增最大自增值
     */
    int INNER_ACCOUNT_NO_MAX_INC = 9999;

}
