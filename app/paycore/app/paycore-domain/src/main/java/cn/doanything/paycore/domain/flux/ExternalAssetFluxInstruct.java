package cn.doanything.paycore.domain.flux;

import cn.doanything.paycore.types.asset.AssetInfo;
import cn.doanything.paycore.types.funds.FundAction;
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
