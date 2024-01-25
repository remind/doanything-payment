package cn.doanything.payment.domain.flux;

import cn.doanything.payment.types.asset.BalanceAsset;
import lombok.Data;

/**
 * @author wxj
 * 2024/1/25
 */
@Data
public class BalanceFluxInstruct extends FluxInstruct {

    /**
     * 借记
     */
    private BalanceAsset debitAsset;

    /**
     * 贷记
     */
    private BalanceAsset creditAsset;
}
