package cn.doanything.commons.payment.asset;

import cn.doanything.commons.lang.utils.EnumUtil;
import cn.hutool.json.JSONUtil;

/**
 * @author wxj
 * 2024/1/15
 */
public abstract class AssetInfo {

    /**
     * 获取资产类型
     *
     * @return
     */
    public abstract AssetType getAssetType();

    public String toJsonStr() {
        return JSONUtil.toJsonStr(this);
    }

    public static AssetInfo parse(String assetTypeCode, String jsonStr) {
        switch (EnumUtil.getByCode(AssetType.class, assetTypeCode)) {
            case BANKCARD:
                return JSONUtil.toBean(jsonStr, BankCardAsset.class);
            case BALANCE:
                return JSONUtil.toBean(jsonStr, BalanceAsset.class);
            default:
                return null;
        }
    }

}
