package cn.doanything.paycore.domain.asset.channel;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.types.asset.AssetInfo;
import cn.doanything.paycore.types.asset.AssetType;
import cn.doanything.paycore.types.funds.FundAction;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wxj
 * 2024/1/25
 */
@Data
public class ChannelFluxInstruction extends FluxInstruction {

    private AssetInfo assetInfo;

    private FundAction fundAction;

    private String clearingAccountNo;

    private LocalDateTime clearDate;


    @Override
    public AssetType getAssetType() {
        return assetInfo.getAssetType();
    }
}
