package cn.doanything.payment.domain.flux;

import cn.doanything.payment.types.asset.AssetInfo;
import cn.doanything.payment.types.funds.FundAction;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wxj
 * 2024/1/25
 */
@Data
public class ExternalAssetFluxInstruct extends AssetFluxInstruct {

    private AssetInfo assetInfo;

    private FundAction fundAction;

    private String clearingAccountNo;

    private LocalDateTime clearDate;
}
