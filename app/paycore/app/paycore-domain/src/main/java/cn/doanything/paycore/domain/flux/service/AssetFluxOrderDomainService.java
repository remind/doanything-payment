package cn.doanything.paycore.domain.flux.service;

import cn.doanything.paycore.domain.flux.AssetFluxInstruct;
import cn.doanything.paycore.domain.flux.AssetFluxOrder;

/**
 * @author wxj
 * 2024/1/26
 */
public interface AssetFluxOrderDomainService {

    void failHandle(AssetFluxOrder assetFluxOrder, AssetFluxInstruct failInstruct);
    void reverse(AssetFluxOrder assetFluxOrder, AssetFluxInstruct failInstruct);
}
