package cn.doanything.paycore.types.asset;

import lombok.Data;

/**
 * @author wxj
 * 2024/1/21
 */
@Data
public class BankCardAsset extends AssetInfo {

    private String bankCardNo;

    private String bankName;

    public BankCardAsset() {
    }

    public BankCardAsset(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    @Override
    public AssetType getAssetType() {
        return AssetType.BANKCARD;
    }
}
