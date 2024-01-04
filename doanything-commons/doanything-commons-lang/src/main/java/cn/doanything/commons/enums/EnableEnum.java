package cn.doanything.commons.enums;

/**
 * @author wxj
 * 2024/1/4
 */
public enum EnableEnum implements CodeEnum {

    ENABLE("1", "启用"),
    DISABLE("0", "禁用"),
    ;

    private final String code;

    private final String displayName;

    EnableEnum(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }
}
