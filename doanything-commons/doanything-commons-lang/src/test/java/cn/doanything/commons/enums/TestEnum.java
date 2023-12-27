package cn.doanything.commons.enums;

/**
 * @author wxj
 * 2023/12/27
 */
public enum TestEnum implements CodeEnum {

    PERSONAL("1", "对私"),
    COMPANY("2", "对公");

    private final String code;
    private final String displayName;

    TestEnum(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;

    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}