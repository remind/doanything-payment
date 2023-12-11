package cn.doanything.member.types;

/**
 * 性别枚举
 *
 * @author wxj
 * 2023/12/10
 */
public enum Gender {

    UNKNOWN(0, "保密"), MALE(1, "男性"), FEMALE(2, "女性");

    /**
     * 代码,只能为1位正整数
     */
    private final int code;
    /**
     * 信息
     */
    private final String message;

    Gender(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     *
     * @param code
     * @return
     */
    public static Gender getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (Gender certType : Gender.values()) {
            if (certType.getCode() == code) {
                return certType;
            }
        }

        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
