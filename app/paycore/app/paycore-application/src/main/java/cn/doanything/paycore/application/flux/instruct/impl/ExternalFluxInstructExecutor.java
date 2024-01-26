package cn.doanything.paycore.application.flux.instruct.impl;

import cn.doanything.paycore.application.flux.instruct.ExecutorResult;
import cn.doanything.paycore.application.flux.instruct.FluxInstructExecutor;
import cn.doanything.paycore.domain.PaymentConstants;
import cn.doanything.paycore.domain.flux.*;
import cn.doanything.paycore.types.asset.BalanceAsset;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wxj
 * 2024/1/26
 */
@Component
public class ExternalFluxInstructExecutor implements FluxInstructExecutor {
    @Override
    public ExecutorResult execute(FluxOrder fluxOrder, FluxInstruction fluxInstruction) {
        ExternalFluxInstruction externalFluxInstruct = (ExternalFluxInstruction) fluxInstruction;
        ExecutorResult result = new ExecutorResult();
        String clearingAccountNo = "clearingAccountNo";
        externalFluxInstruct.setClearingAccountNo(clearingAccountNo);
        result.setStatus(InstructStatus.SUCCESS);
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
