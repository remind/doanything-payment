package cn.doanything.paycore.domain.asset.channel;

import cn.doanything.paycore.domain.PaymentConstants;
import cn.doanything.paycore.domain.asset.FluxInstructionExecutor;
import cn.doanything.paycore.domain.asset.FluxResult;
import cn.doanything.paycore.domain.asset.balance.BalanceFluxInstruction;
import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.domain.flux.InstructStatus;
import cn.doanything.paycore.domain.flux.InstructionType;
import cn.doanything.paycore.types.PayStatus;
import cn.doanything.paycore.types.asset.BalanceAsset;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wxj
 * 2024/1/27
 */
@Component
public class ChannelFluxInstructExecutor implements FluxInstructionExecutor {
    @Override
    public FluxResult execute(FluxOrder fluxOrder, FluxInstruction fluxInstruction) {
        ChannelFluxInstruction externalFluxInstruct = (ChannelFluxInstruction) fluxInstruction;
        FluxResult result = new FluxResult();
        String clearingAccountNo = "clearingAccountNo";
        externalFluxInstruct.setClearingAccountNo(clearingAccountNo);
        result.setStatus(PayStatus.SUCCESS);
        result.setNewFluxInstructions(List.of(buildClearingFluxInstruct(externalFluxInstruct, externalFluxInstruct.getClearingAccountNo())));
        return result;
    }

    private BalanceFluxInstruction buildClearingFluxInstruct(FluxInstruction fluxInstruction, String clearAccountNo) {
        BalanceFluxInstruction newFluxInstruct = new BalanceFluxInstruction();
        newFluxInstruct.setFluxOrderId(fluxInstruction.getFluxOrderId());
        newFluxInstruct.setInstructionType(InstructionType.FORWARD);
        newFluxInstruct.setRelatedInstructionId(fluxInstruction.getInstructionId());
        newFluxInstruct.setFundDetailId(fluxInstruction.getFundDetailId());
        newFluxInstruct.setAmount(fluxInstruction.getAmount());
        newFluxInstruct.setDebitAsset(new BalanceAsset(PaymentConstants.INNER_MEMBER_ID, clearAccountNo));
        newFluxInstruct.setCreditAsset(new BalanceAsset(PaymentConstants.INNER_MEMBER_ID, PaymentConstants.TRANSITION_ACCOUNT));
        return newFluxInstruct;
    }
}
