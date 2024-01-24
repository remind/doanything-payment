package cn.doanything.payment.domain.flux;

import cn.doanything.payment.types.asset.AssetInfo;
import cn.doanything.payment.types.funds.FluxAction;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wxj
 * 2024/1/23
 */
@Data
public class ExternalAssetFluxInstruct extends AssetFluxInstruct {

    private AssetInfo assetInfo;

    private FluxAction fluxAction;

    private String clearingAccountNo;

    private LocalDateTime clearDate;
}
