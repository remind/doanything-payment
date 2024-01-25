package cn.doanything.payment.domain.flux;

import cn.doanything.payment.types.asset.AssetInfo;
import cn.doanything.payment.types.funds.FluxAction;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wxj
 * 2024/1/25
 */
@Data
public class ExternalFluxInstruct extends FluxInstruct {

    private AssetInfo assetInfo;

    private FluxAction fluxAction;

    private String clearingAccountNo;

    private LocalDateTime clearDate;
}
