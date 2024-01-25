package cn.doanything.payment.application.flux;

import cn.doanything.payment.application.flux.builder.AssetFluxOrderBuilder;
import cn.doanything.payment.domain.BasePayOrder;
import cn.doanything.payment.domain.flux.AssetFluxFlow;
import cn.doanything.payment.domain.flux.AssetFluxOrder;
import cn.doanything.payment.domain.flux.FluxInstruct;
import cn.doanything.payment.domain.flux.service.FluxDomainService;
import cn.doanything.payment.types.asset.AssetType;
import cn.doanything.payment.types.funds.FundDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxj
 * 2024/1/24
 */
@Service
public class AssetFluxFlowService {


    @Autowired
    private AssetFluxOrderBuilder assetFluxOrderBuilder;

    @Autowired
    private FluxDomainService fluxDomainService;

    public void execute(BasePayOrder payOrder) {
        AssetFluxOrder assetFluxOrder = assetFluxOrderBuilder.build(payOrder);
        fluxDomainService.execute(assetFluxOrder, payOrder.getPayerDetails(), payOrder.getPayeeDetails());
    }
    public void execute(AssetFluxOrder assetFluxOrder, AssetFluxFlow assetFluxFlow) {

        if (assetFluxFlow.getAssetType().equals(AssetType.BALANCE)) {

        }
        // 0. 根据assetType选择构造器和执行器
        // 1. 构造fluxInstruct
        // 2. 保存fluxInstruct
        // 3. 执行fluxInstruct
    }
}
