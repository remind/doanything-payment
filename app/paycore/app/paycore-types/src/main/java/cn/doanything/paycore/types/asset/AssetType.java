package cn.doanything.paycore.types.asset;

import cn.doanything.commons.enums.CodeEnum;
import lombok.Getter;

/**
 * @author wxj
 * 2024/1/15
 */
@Getter
public enum AssetType implements CodeEnum {

    BANKCARD("BANKCARD", AssetTypeCategory.EXTERNAL, "银行卡"),
    BALANCE("BALANCE", AssetTypeCategory.ACCOUNTING, "余额"),
    WX("WX", AssetTypeCategory.EXTERNAL, "微信");

    private String code;

    private AssetTypeCategory assetTypeCategory;

    private String displayName;

    AssetType(String code, AssetTypeCategory assetTypeCategory, String displayName) {
        this.code = code;
        this.assetTypeCategory = assetTypeCategory;
        this.displayName = displayName;
    }
}
