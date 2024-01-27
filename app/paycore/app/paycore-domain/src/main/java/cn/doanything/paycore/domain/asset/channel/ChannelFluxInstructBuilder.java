package cn.doanything.paycore.domain.asset.channel;

import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.asset.FluxInstructBuilder;
import cn.doanything.paycore.types.funds.FundDetail;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * 2024/1/26
 */
@Component
public class ChannelFluxInstructBuilder implements FluxInstructBuilder {
    @Override
    public FluxInstruction build(FundDetail fundDetail) {
        ChannelFluxInstruction fluxInstruction = new ChannelFluxInstruction();
        fluxInstruction.setAssetInfo(fundDetail.getAssetInfo());
        fluxInstruction.setFundAction(fundDetail.getFundAction());
        return fluxInstruction;
    }
}
