package cn.doanything.member.types;

/**
 * 会员类型
 *
 * @author wxj
 * 2023/12/10
 */
public enum MemberType {
    PERSONAL(1, "10", "个人"), COMPANY(2, "20", "企业");

    /**
     * 代码，只能为一位正整数
     */
    private final int code;

    /**
     * ID前缀,2位数字
     */
    private final String prefix;
    /**
     * 信息
     */
    private final String message;

    MemberType(int code, String prefix, String message) {
        this.code = code;
        this.prefix = prefix;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     *
     * @param code
     * @return
     */
    public static MemberType getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (MemberType memberType : MemberType.values()) {
            if (memberType.getCode() == code) {
                return memberType;
            }
        }

        return null;
    }

    public int getCode() {
        return code;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getMessage() {
        return message;
    }
}
