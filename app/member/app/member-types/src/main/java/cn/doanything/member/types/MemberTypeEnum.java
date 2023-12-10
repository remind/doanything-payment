package cn.doanything.member.types;

/**
 * 会员类型
 * @author wxj
 * 2023/12/10
 */
public enum MemberTypeEnum {
    PERSONAL(1L, "10","个人"), COMPANY(2L, "20","企业");

    /** 代码 */
    private final Long   code;

    /**
     * ID前缀
     */
    private final String prefix;
    /** 信息 */
    private final String message;

    MemberTypeEnum(Long code, String prefix, String message) {
        this.code = code;
        this.prefix = prefix;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static MemberTypeEnum getByCode(Long code) {
        if (code == null) {
            return null;
        }

        for (MemberTypeEnum memberType : MemberTypeEnum.values()) {
            if (memberType.getCode().equals(code)) {
                return memberType;
            }
        }

        return null;
    }

    public Long getCode() {
        return code;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getMessage() {
        return message;
    }
}
