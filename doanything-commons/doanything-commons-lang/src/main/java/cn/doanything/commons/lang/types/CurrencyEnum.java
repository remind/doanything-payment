package cn.doanything.commons.lang.types;

/**
 * @author wxj
 * 2023/12/19
 */
public enum CurrencyEnum {

    CNY("CNY", new String(new char[] { 0xffe5 }), "人民币")
    ;

    private final String code;

    private final String symbol;

    private final String name;

    CurrencyEnum(String code, String symbol, String name) {
        this.code = code;
        this.symbol = symbol;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
}
