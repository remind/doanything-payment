package cn.doanything.payment.types.asset;

import lombok.Data;

/**
 * @author wxj
 * 2024/1/15
 */
@Data
public class BalanceAsset extends AssetInfo {

    /**
     * 用户ID
     */
    private String memberId;

    /**
     * 账户ID
     */
    private String accountNo;

    @Override
    public AssetType getAssetType() {
        return AssetType.BALANCE;
    }
}
