package cn.doanything.paycore.domain.flux.balance;

import cn.doanything.paycore.domain.PaymentConstants;
import cn.doanything.paycore.domain.flux.BalanceFluxInstruction;
import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.processor.FluxInstructProcessor;
import cn.doanything.paycore.types.asset.BalanceAsset;
import cn.doanything.paycore.types.asset.BelongTo;
import cn.doanything.paycore.types.funds.FundDetail;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * 2024/1/26
 */
@Component
public class BalanceFluxInstructProcessor implements FluxInstructProcessor {
    @Override
    public FluxInstruction build(FundDetail fundDetail) {
        BalanceFluxInstruction fluxInstruction = new BalanceFluxInstruction();
        BalanceAsset balanceAsset = (BalanceAsset) fundDetail.getAssetInfo();
        if (fundDetail.getBelongTo() == BelongTo.PAYER) {
            fluxInstruction.setDebitAsset(balanceAsset);
            fluxInstruction.setCreditAsset(new BalanceAsset(PaymentConstants.INNER_MEMBER_ID, PaymentConstants.TRANSITION_ACCOUNT));
        } else {
            fluxInstruction.setCreditAsset(balanceAsset);
            fluxInstruction.setDebitAsset(new BalanceAsset(PaymentConstants.INNER_MEMBER_ID, PaymentConstants.TRANSITION_ACCOUNT));
        }
        return fluxInstruction;
    }
}
