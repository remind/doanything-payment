package cn.doanything.member.types;

/**
 * 性别枚举
 * @author wxj
 * 2023/12/10
 */
public enum GenderEnum {

    UNKOWN(0L, "保密"), MALE(1L, "男性"), FEMALE(2L, "女性");

    /** 代码 */
    private final Long   code;
    /** 信息 */
    private final String message;

    GenderEnum(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static GenderEnum getByCode(Long code) {
        if (code == null) {
            return null;
        }

        for (GenderEnum certType : GenderEnum.values()) {
            if (certType.getCode().equals(code)) {
                return certType;
            }
        }

        return null;
    }

    public Long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
