package cn.doanything.commons.enums;

/**
 * 系统编码枚举
 * @author remind
 * 2023年05月13日 22:03
 */
public enum SystemCodeEnums {

    PAYMENT("001", "PAYMENT"),

    ;

    private String code;

    private String name;

    SystemCodeEnums(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
