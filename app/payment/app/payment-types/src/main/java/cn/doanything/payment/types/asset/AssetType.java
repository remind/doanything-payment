package cn.doanything.payment.types.asset;

import cn.doanything.commons.enums.CodeEnum;

/**
 * @author wxj
 * 2024/1/15
 */
public enum AssetType implements CodeEnum  {

    BANKCARD("BANKCARD", "银行卡"),
    BALANCE("BALANCE", "余额"),
    WX("WX","微信");


    private String code;

    private String displayName;

    AssetType(String code, String displayName) {
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
