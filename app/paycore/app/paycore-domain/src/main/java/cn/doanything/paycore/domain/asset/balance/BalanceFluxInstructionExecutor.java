package cn.doanything.paycore.domain.asset.balance;

import cn.doanything.paycore.domain.asset.FluxInstructionExecutor;
import cn.doanything.paycore.domain.asset.FluxResult;
import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.types.PayStatus;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * 2024/1/27
 */
@Component
public class BalanceFluxInstructionExecutor implements FluxInstructionExecutor {
    @Override
    public FluxResult execute(FluxOrder fluxOrder, FluxInstruction fluxInstruction) {
        BalanceFluxInstruction balanceFluxInstruct = (BalanceFluxInstruction) fluxInstruction;
        FluxResult result = new FluxResult();
        result.setStatus(PayStatus.SUCCESS);
        return result;
    }
}
