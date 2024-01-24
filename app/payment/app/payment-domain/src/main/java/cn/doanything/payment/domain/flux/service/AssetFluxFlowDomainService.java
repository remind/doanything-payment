package cn.doanything.payment.domain.flux.service;

import cn.doanything.payment.domain.flux.AssetFluxFlow;
import cn.doanything.payment.domain.flux.AssetFluxOrder;
import cn.doanything.payment.domain.service.IdGeneratorDomainService;
import cn.doanything.payment.types.IdType;
import cn.doanything.payment.types.asset.AssetType;
import cn.doanything.payment.types.asset.BelongTo;
import cn.doanything.payment.types.funds.FundDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wxj
 * 2024/1/22
 */
@Service
public class AssetFluxFlowDomainService {
    @Autowired
    private IdGeneratorDomainService idGeneratorDomainService;

    public void fillFluxFlow(AssetFluxOrder assetFluxOrder, List<FundDetail> fundDetails) {
        List<AssetFluxFlow> assetFluxFlows = new ArrayList<>();
        AtomicInteger sort = new AtomicInteger(1);

        // 排序先只看BelongTo
        fundDetails.sort((a, b) -> {
            if (a.getBelongTo() != b.getBelongTo()) {
                if (a.getBelongTo() == BelongTo.PAYER) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return 0;
        });

        for (FundDetail fundDetail : fundDetails) {
            AssetFluxFlow lastAssetFluxFlow = getLastFlow(assetFluxFlows);
            String fluxFlowId = idGeneratorDomainService.genSubPayOrder(assetFluxOrder.getPaymentId(), IdType.FLUX_FLOW_ID);
            if (lastAssetFluxFlow != null) {
                if (fundDetail.getAssetInfo().getAssetType() == AssetType.BALANCE && lastAssetFluxFlow.getAssetType() == fundDetail.getAssetInfo().getAssetType()) {
                    lastAssetFluxFlow.getFundDetailIds().add(fundDetail.getDetailId());
                    continue;
                }
                lastAssetFluxFlow.setNextFluxFlowId(fluxFlowId);
            }
            AssetFluxFlow assetFluxFlow = new AssetFluxFlow();
            assetFluxFlow.setFluxFlowId(fluxFlowId);
            assetFluxFlow.setFluxOrderId(assetFluxOrder.getFluxOrderId());
            assetFluxFlow.setAssetType(fundDetail.getAssetInfo().getAssetType());
            assetFluxFlow.setSort(sort.getAndIncrement());
            assetFluxFlow.getFundDetailIds().add(fundDetail.getDetailId());
            assetFluxFlows.add(assetFluxFlow);
        }
        assetFluxOrder.setAssetFluxFlows(assetFluxFlows);
    }

    private AssetFluxFlow getLastFlow(List<AssetFluxFlow> assetFluxFlows) {
        if (CollectionUtils.isEmpty(assetFluxFlows)) {
            return null;
        }
        return assetFluxFlows.get(assetFluxFlows.size() - 1);
    }

}
