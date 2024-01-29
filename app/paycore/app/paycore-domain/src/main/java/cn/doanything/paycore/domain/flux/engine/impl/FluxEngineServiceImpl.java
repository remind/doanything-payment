package cn.doanything.paycore.domain.flux.engine.impl;

import cn.doanything.paycore.domain.asset.FluxInstructionExecutor;
import cn.doanything.paycore.domain.asset.FluxResult;
import cn.doanything.paycore.domain.asset.factory.AssetFluxFactory;
import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.domain.flux.chain.InstructChainService;
import cn.doanything.paycore.domain.flux.engine.FluxEngineService;
import cn.doanything.paycore.domain.flux.engine.processor.FluxResultProcessor;
import cn.doanything.paycore.domain.flux.engine.processor.InstructResultProcessor;
import cn.doanything.paycore.domain.flux.service.FluxInstructDomainService;
import cn.doanything.paycore.domain.repository.FluxInstructionRepository;
import cn.doanything.paycore.domain.repository.FluxOrderRepository;
import cn.doanything.paycore.types.PayResult;
import cn.doanything.paycore.types.PayStatus;
import cn.doanything.paycore.types.asset.AssetType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2024/1/27
 */
@Service
@Slf4j
public class FluxEngineServiceImpl implements FluxEngineService {

    @Autowired
    private AssetFluxFactory assetFluxFactory;

    @Autowired
    private FluxInstructionRepository instructionRepository;

    @Autowired
    private FluxOrderRepository fluxOrderRepository;

    @Autowired
    private InstructChainService instructChainService;

    @Autowired
    private FluxInstructDomainService instructDomainService;

    @Autowired
    private FluxResultProcessor fluxResultProcessor;

    @Autowired
    private InstructResultProcessor instructResultProcessor;

    @Override
    public PayResult process(FluxOrder fluxOrder) {
        FluxInstruction executeInstruction = instructChainService.getExecuteFluxInstruct(fluxOrder);
        FluxResult fluxResult = null;
        boolean isContinue = true;
        while (executeInstruction != null && isContinue) {
            AssetType assetType = executeInstruction.getAssetType();
            FluxInstructionExecutor instructionExecutor = assetFluxFactory.getFluxInstructionExecutor(assetType);
            fluxResult = instructionExecutor.execute(fluxOrder, executeInstruction);
            isContinue = instructResultProcessor.process(fluxOrder, executeInstruction, fluxResult);
            if (isContinue) {
                executeInstruction = instructChainService.getExecuteFluxInstruct(fluxOrder);
            }
        }
        fluxResultProcessor.process(fluxOrder, executeInstruction, fluxResult);
        return convertToPayResult(fluxResult);
    }

    private PayResult convertToPayResult(FluxResult fluxResult) {
        PayResult payResult = new PayResult();
        if (fluxResult == null) {
            payResult.setPayStatus(PayStatus.SUCCESS);
        } else {
            payResult.setPayStatus(fluxResult.getStatus());
            payResult.setResultMessage(fluxResult.getResultMessage());
            payResult.setResultCode(fluxResult.getResultCode());
        }
        return payResult;
    }

}
