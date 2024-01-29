package cn.doanything.paycore.domain.asset.balance;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.types.asset.AssetInfo;
import cn.doanything.paycore.types.asset.AssetType;
import cn.doanything.paycore.types.asset.BalanceAsset;
import cn.doanything.paycore.types.funds.FundAction;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wxj
 * 2024/1/25
 */
@Data
public class BalanceFluxInstruction extends FluxInstruction {

    /**
     * 借记
     */
    private BalanceAsset debitAsset;

    /**
     * 贷记
     */
    private BalanceAsset creditAsset;

    @Override
    public AssetType getAssetType() {
        return AssetType.BALANCE;
    }

    @Override
    public void setAssetInfo(String assetInfo) {
        if (StrUtil.isNotBlank(assetInfo)) {
            Map<String, BalanceAsset> assetInfoMap = JSONUtil.toBean(assetInfo, Map.class, true);
            this.debitAsset = assetInfoMap.get("debit");
            this.creditAsset = assetInfoMap.get("credit");
        }
        super.setAssetInfo(assetInfo);
    }

    @Override
    public String getAssetInfo() {
        Map<String, BalanceAsset> assetInfoMap = new HashMap<>();
        if (this.debitAsset != null) {
            assetInfoMap.put("debit", this.debitAsset);
        }
        if (this.creditAsset != null) {
            assetInfoMap.put("credit", this.creditAsset);
        }
        return JSONUtil.toJsonStr(assetInfoMap);
    }
}
