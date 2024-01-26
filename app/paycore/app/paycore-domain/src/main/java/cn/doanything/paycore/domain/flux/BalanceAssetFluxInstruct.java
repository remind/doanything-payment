package cn.doanything.paycore.domain.flux;

import cn.doanything.paycore.types.asset.BalanceAsset;
import lombok.Data;

/**
 * @author wxj
 * 2024/1/25
 */
@Data
public class BalanceAssetFluxInstruct extends AssetFluxInstruct {

    /**
     * 借记
     */
    private BalanceAsset debitAsset;

    /**
     * 贷记
     */
    private BalanceAsset creditAsset;
}
