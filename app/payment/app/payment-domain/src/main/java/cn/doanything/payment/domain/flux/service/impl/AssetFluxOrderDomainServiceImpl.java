package cn.doanything.payment.domain.flux.service.impl;

import cn.doanything.commons.lang.utils.AssertUtil;
import cn.doanything.payment.domain.flux.AssetFluxInstruct;
import cn.doanything.payment.domain.flux.AssetFluxOrder;
import cn.doanything.payment.domain.flux.InstructStatus;
import cn.doanything.payment.domain.flux.InstructType;
import cn.doanything.payment.domain.flux.service.AssetFluxInstructDomainService;
import cn.doanything.payment.domain.flux.service.AssetFluxOrderDomainService;
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
public class AssetFluxOrderDomainServiceImpl implements AssetFluxOrderDomainService {

    @Autowired
    private AssetFluxInstructDomainService instructDomainService;

    @Override
    public void failHandle(AssetFluxOrder assetFluxOrder, AssetFluxInstruct failInstruct) {
        if (failInstruct.getInstructType() == InstructType.FORWARD) {
            reverse(assetFluxOrder, failInstruct);
        } else {
            // 逆向的再失败
        }
    }

    @Override
    public void reverse(AssetFluxOrder assetFluxOrder, AssetFluxInstruct failInstruct) {
        AssertUtil.isTrue(failInstruct.getInstructType() == InstructType.FORWARD, "只有正向才能逆向");
        assetFluxOrder.deleteAfterFluxInstruct(failInstruct);
        List<AssetFluxInstruct> forwardInstructs = assetFluxOrder.getAllFluxInstructs().stream()
                .filter(assetFluxInstruct -> assetFluxInstruct.getStatus() == InstructStatus.SUCCESS)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(forwardInstructs)) {
            Collections.reverse(forwardInstructs);
            forwardInstructs.forEach(assetFluxInstruct -> assetFluxOrder.addFluxInstruct(instructDomainService.reverse(assetFluxInstruct)));
        }
    }
}
