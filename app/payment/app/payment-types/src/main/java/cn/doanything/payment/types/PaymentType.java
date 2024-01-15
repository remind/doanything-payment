package cn.doanything.payment.types;

import cn.doanything.commons.enums.CodeEnum;

/**
 * @author wxj
 * 2024/1/15
 */
public enum PaymentType implements CodeEnum {

    INSTANT("INSTANT", "直接支付");
    ;

    private String code;

    private String displayName;

    PaymentType(String code, String displayName) {
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
