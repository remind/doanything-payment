package cn.doanything.paycore.domain.flux.external;

import cn.doanything.paycore.domain.flux.ExternalFluxInstruction;
import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.processor.FluxInstructProcessor;
import cn.doanything.paycore.types.funds.FundDetail;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * 2024/1/26
 */
@Component
public class ExternalFluxInstructProcessor implements FluxInstructProcessor {
    @Override
    public FluxInstruction build(FundDetail fundDetail) {
        ExternalFluxInstruction fluxInstruction = new ExternalFluxInstruction();
        fluxInstruction.setAssetInfo(fundDetail.getAssetInfo());
        fluxInstruction.setFundAction(fundDetail.getFundAction());
        return fluxInstruction;
    }
}
