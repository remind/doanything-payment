package cn.doanything.commons.payment.asset;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/1/15
 */
@Getter
public enum AssetType implements CodeEnum {

    BANKCARD("BANKCARD", "银行卡"),
    BALANCE("BALANCE", "余额"),
    WX("WX", "微信");

    private String code;

    private String displayName;

    AssetType(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}
