package cn.doanything.paycore.application.flux.instruct.impl;

import cn.doanything.paycore.application.flux.instruct.ExecutorResult;
import cn.doanything.paycore.application.flux.instruct.FluxInstructExecutor;
import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.domain.asset.balance.BalanceFluxInstruction;
import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.InstructStatus;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * 2024/1/26
 */
@Component
public class BalanceInstructExecutor implements FluxInstructExecutor {
    @Override
    public ExecutorResult execute(FluxOrder fluxOrder, FluxInstruction fluxInstruction) {
        BalanceFluxInstruction balanceFluxInstruct = (BalanceFluxInstruction) fluxInstruction;
        ExecutorResult result = new ExecutorResult();
        result.setStatus(InstructStatus.SUCCESS);
        return result;
    }
}
