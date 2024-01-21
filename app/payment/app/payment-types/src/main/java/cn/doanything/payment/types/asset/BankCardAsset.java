package cn.doanything.payment.types.asset;

import lombok.Data;

/**
 * @author wxj
 * 2024/1/21
 */
@Data
public class BankCardAsset extends AssetInfo {

    private String bankCardNo;

    private String bankName;

    @Override
    public AssetType getAssetType() {
        return AssetType.BANKCARD;
    }
}
