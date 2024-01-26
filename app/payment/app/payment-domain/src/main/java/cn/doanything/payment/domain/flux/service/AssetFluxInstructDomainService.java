package cn.doanything.payment.domain.flux.service;

import cn.doanything.payment.domain.flux.AssetFluxInstruct;

/**
 * @author wxj
 * 2024/1/26
 */
public interface AssetFluxInstructDomainService {

    public AssetFluxInstruct reverse(AssetFluxInstruct assetFluxInstruct);
}
