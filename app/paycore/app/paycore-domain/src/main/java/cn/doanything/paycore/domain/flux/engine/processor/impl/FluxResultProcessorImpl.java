package cn.doanything.paycore.domain.flux.engine.processor.impl;

import cn.doanything.commons.lang.utils.AssertUtil;
import cn.doanything.paycore.domain.asset.FluxResult;
import cn.doanything.paycore.domain.flux.*;
import cn.doanything.paycore.domain.flux.chain.InstructChainService;
import cn.doanything.paycore.domain.flux.engine.processor.FluxResultProcessor;
import cn.doanything.paycore.domain.flux.service.FluxInstructDomainService;
import cn.doanything.paycore.domain.repository.FluxInstructionRepository;
import cn.doanything.paycore.domain.repository.FluxOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author wxj
 * 2024/1/29
 */
@Service
public class FluxResultProcessorImpl implements FluxResultProcessor {

    @Autowired
    private FluxInstructionRepository instructionRepository;

    @Autowired
    private FluxOrderRepository fluxOrderRepository;

    @Autowired
    private InstructChainService instructChainService;

    @Autowired
    private FluxInstructDomainService instructDomainService;

    @Override
    public boolean process(FluxOrder fluxOrder, FluxInstruction lastInstruction, FluxResult lastResult) {
        boolean isContinue = false;
        switch (lastResult.getStatus()) {
            case SUCCESS:
                isContinue = successProcess(fluxOrder);
                break;
            case FAIL:
                isContinue = failProcess(fluxOrder, lastInstruction);
                break;
            default:
                fluxOrder.setStatus(FluxOrderStatus.PROCESS);
                break;
        }
        fluxOrderRepository.reStore(fluxOrder);
        return isContinue;
    }

    private boolean successProcess(FluxOrder fluxOrder) {
        long count = fluxOrder.getAllFluxInstructs().stream().filter(i -> i.getStatus() != InstructStatus.SUCCESS).count();
        AssertUtil.isTrue(count == 0, "执行成功，但是指令状态不一致");
        fluxOrder.setStatus(FluxOrderStatus.SUCCESS);
        return false;
    }

    private boolean failProcess(FluxOrder fluxOrder, FluxInstruction lastInstruction) {
        boolean isContinue = false;
        if (lastInstruction.getType() == InstructionType.FORWARD) {
            // 删除所有后置指令
            List<String> deleteIds = instructChainService.deleteAfterFluxInstruct(fluxOrder, lastInstruction.getInstructionId());
            instructionRepository.remove(deleteIds);

            // 生成逆向的
            addReverseInstruct(fluxOrder);
            isContinue = true;
            fluxOrder.setStatus(FluxOrderStatus.FAIL);
        } else {
            // TODO 处理逆向失败的，可以重试或者转差错
        }

        return isContinue;
    }

    private void addReverseInstruct(FluxOrder fluxOrder) {
        List<FluxInstruction> forwardInstructs = fluxOrder.getAllFluxInstructs().stream()
                .filter(assetFluxInstruct -> assetFluxInstruct.getStatus() == InstructStatus.SUCCESS)
                .toList();
        if (!CollectionUtils.isEmpty(forwardInstructs)) {
            Collections.reverse(forwardInstructs);
            forwardInstructs.forEach(assetFluxInstruct -> {
                FluxInstruction reverseInstruct = instructDomainService.createReverseInstruct(assetFluxInstruct);
                instructChainService.addInstruct(fluxOrder, reverseInstruct);
                instructionRepository.store(reverseInstruct);
            });
        }
    }
}
