package cn.doanything.account.types.buffer;

import cn.doanything.commons.enums.CodeEnum;

/**
 * 缓冲规则状态
 * @author wxj
 * 2023/12/25
 */
public enum BufferedRuleStatus implements CodeEnum {
    INVALID("0","无效"),

    VALID("1", "有效"),

    ;

    private final String code;
    private final String displayName;

    private BufferedRuleStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    /**
     * 根据code获取枚举
     *
     * @param code
     * @return BalanceDirection
     */
    public static BufferedRuleStatus getByCode(String code) {
        for (BufferedRuleStatus enumObject : BufferedRuleStatus.values()) {
            if (enumObject.code().equals(code)) {
                return enumObject;
            }
        }
        return null;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String displayName() {
        return this.displayName;
    }
}
