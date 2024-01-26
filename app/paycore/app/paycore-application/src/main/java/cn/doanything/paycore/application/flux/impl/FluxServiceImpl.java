package cn.doanything.paycore.application.flux.impl;

import cn.doanything.paycore.application.flux.FluxService;
import cn.doanything.paycore.application.flux.instruct.ExecutorResult;
import cn.doanything.paycore.application.flux.instruct.FluxInstructExecutor;
import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.domain.flux.BalanceFluxInstruction;
import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.InstructStatus;
import cn.doanything.paycore.domain.flux.service.FluxOrderDomainService;
import cn.doanything.paycore.types.PayResult;
import cn.doanything.paycore.types.PayStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wxj
 * 2024/1/26
 */
@Service
public class FluxServiceImpl implements FluxService {

    @Resource(name = "balanceInstructExecutor")
    private FluxInstructExecutor balanceInstructExecutor;

    @Resource(name = "externalFluxInstructExecutor")
    private FluxInstructExecutor externalFluxInstructExecutor;

    @Autowired
    private FluxOrderDomainService fluxOrderDomainService;

    @Override
    public PayResult process(FluxOrder fluxOrder) {
        ExecutorResult result = null;
        FluxInstruction executeFluxInstruction = fluxOrder.getExecuteFluxInstruct();
        while (executeFluxInstruction != null) {
            if (executeFluxInstruction instanceof BalanceFluxInstruction) {
                result = balanceInstructExecutor.execute(fluxOrder, executeFluxInstruction);
            } else {
                result = externalFluxInstructExecutor.execute(fluxOrder, executeFluxInstruction);
            }
            executeFluxInstruction.setStatus(result.getStatus());
            if (result.getStatus() == InstructStatus.SUCCESS) {
                insertNewFluxInstruct(fluxOrder, executeFluxInstruction, result.getNewFluxInstructions());
                executeFluxInstruction = fluxOrder.getExecuteFluxInstruct();
            } else if (result.getStatus() == InstructStatus.FAIL) {
                fluxOrderDomainService.failHandle(fluxOrder, executeFluxInstruction);
                executeFluxInstruction = fluxOrder.getExecuteFluxInstruct();
            } else {
                executeFluxInstruction = null;
            }
        }
        return convertToPayResult(fluxOrder, result);
    }

    private void insertNewFluxInstruct(FluxOrder fluxOrder, FluxInstruction fluxInstruction, List<FluxInstruction> newFluxInstructions) {
        FluxInstruction afterFluxInstruction = fluxInstruction;
        for (FluxInstruction newFluxInstruction : newFluxInstructions) {
            fluxOrder.insertFluxInstruct(afterFluxInstruction, newFluxInstruction);
            afterFluxInstruction = newFluxInstruction;
        }
    }

    private PayResult convertToPayResult(FluxOrder fluxOrder, ExecutorResult executorResult) {
        PayResult payResult = new PayResult();

        if (executorResult == null || executorResult.getStatus() == InstructStatus.SUCCESS) {
            payResult.setPayStatus(PayStatus.SUCCESS);
        } else if (executorResult.getStatus() == InstructStatus.FAIL) {
            payResult.setPayStatus(PayStatus.FAIL);
        } else {
            payResult.setPayStatus(PayStatus.PROCESSING);
        }
        return payResult;
    }
}
