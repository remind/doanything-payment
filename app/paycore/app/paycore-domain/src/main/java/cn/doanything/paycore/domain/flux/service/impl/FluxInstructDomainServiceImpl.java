package cn.doanything.paycore.domain.flux.service.impl;

import cn.doanything.paycore.domain.asset.balance.BalanceFluxInstruction;
import cn.doanything.paycore.domain.asset.channel.ChannelFluxInstruction;
import cn.doanything.paycore.domain.flux.*;
import cn.doanything.paycore.domain.flux.service.FluxInstructDomainService;
import cn.doanything.paycore.domain.service.IdGeneratorService;
import cn.doanything.paycore.types.IdType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2024/1/26
 */
@Service
public class FluxInstructDomainServiceImpl implements FluxInstructDomainService {

    @Autowired
    private IdGeneratorService idGeneratorService;
    @Override
    public FluxInstruction createReverseInstruct(FluxInstruction fluxInstruction) {
        FluxInstruction reverseInstruct;
        if (fluxInstruction instanceof BalanceFluxInstruction) {
            reverseInstruct = new BalanceFluxInstruction();
            fillBalanceInstruct((BalanceFluxInstruction) reverseInstruct, (BalanceFluxInstruction) fluxInstruction);
        } else {
            reverseInstruct = new ChannelFluxInstruction();
            fillExternalInstruct((ChannelFluxInstruction) reverseInstruct, (ChannelFluxInstruction) fluxInstruction);
        }
        reverseInstruct.setInstructionId(idGeneratorService.genIdByRelateId(fluxInstruction.getFluxOrderId(), IdType.FLUX_INSTRUCT_ID));
        reverseInstruct.setInstructionType(InstructionType.REVERSE);
        reverseInstruct.setAmount(fluxInstruction.getAmount());
        reverseInstruct.setStatus(InstructStatus.INIT);
        reverseInstruct.setFundDetailId(fluxInstruction.getFundDetailId());
        reverseInstruct.setRelatedInstructionId(fluxInstruction.getInstructionId());
        return reverseInstruct;
    }

    private void fillBalanceInstruct(BalanceFluxInstruction reverseInstruct, BalanceFluxInstruction forwardInstruct) {
        reverseInstruct.setDebitAsset(forwardInstruct.getCreditAsset());
        reverseInstruct.setCreditAsset(forwardInstruct.getDebitAsset());
    }

    private void fillExternalInstruct(ChannelFluxInstruction reverseInstruct, ChannelFluxInstruction forwardInstruct) {
        reverseInstruct.setAssetInfo(forwardInstruct.getAssetInfo());
        reverseInstruct.setFundAction(forwardInstruct.getFundAction().reverse());
    }
}
