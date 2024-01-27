package cn.doanything.paycore.domain.flux.service.impl;

import cn.doanything.commons.lang.utils.AssertUtil;
import cn.doanything.paycore.domain.asset.FluxInstructionExecutor;
import cn.doanything.paycore.domain.asset.FluxResult;
import cn.doanything.paycore.domain.flux.FluxInstruction;
import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.domain.flux.InstructStatus;
import cn.doanything.paycore.domain.flux.InstructionType;
import cn.doanything.paycore.domain.flux.service.AbstractFluxService;
import cn.doanything.paycore.domain.flux.service.FluxInstructDomainService;
import cn.doanything.paycore.domain.flux.service.FluxOrderDomainService;
import cn.doanything.paycore.types.asset.AssetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wxj
 * 2024/1/26
 */
@Service
public class FluxOrderDomainServiceImpl extends AbstractFluxService implements FluxOrderDomainService {

    @Autowired
    private FluxInstructDomainService instructDomainService;

    @Override
    public void failHandle(FluxOrder fluxOrder, FluxInstruction failInstruct) {
        if (failInstruct.getInstructionType() == InstructionType.FORWARD) {
            reverse(fluxOrder, failInstruct);
        } else {
            // 逆向的再失败
        }
    }

    @Override
    public void reverse(FluxOrder fluxOrder, FluxInstruction failInstruct) {
        AssertUtil.isTrue(failInstruct.getInstructionType() == InstructionType.FORWARD, "只有正向才能逆向");
        fluxOrder.deleteAfterFluxInstruct(failInstruct);
        List<FluxInstruction> forwardInstructs = fluxOrder.getAllFluxInstructs().stream()
                .filter(assetFluxInstruct -> assetFluxInstruct.getStatus() == InstructStatus.SUCCESS)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(forwardInstructs)) {
            Collections.reverse(forwardInstructs);
            forwardInstructs.forEach(assetFluxInstruct -> fluxOrder.addFluxInstruct(instructDomainService.createReverseInstruct(assetFluxInstruct)));
        }
    }
}
