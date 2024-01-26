package cn.doanything.payment.domain.flux.service.impl;

import cn.doanything.payment.domain.flux.*;
import cn.doanything.payment.domain.flux.service.AssetFluxInstructDomainService;
import cn.doanything.payment.domain.service.IdGeneratorDomainService;
import cn.doanything.payment.types.IdType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * 2024/1/26
 */
@Service
public class AssetFluxInstructDomainServiceImpl implements AssetFluxInstructDomainService {

    @Autowired
    private IdGeneratorDomainService idGeneratorDomainService;
    @Override
    public AssetFluxInstruct reverse(AssetFluxInstruct assetFluxInstruct) {
        AssetFluxInstruct reverseInstruct;
        if (assetFluxInstruct instanceof BalanceAssetFluxInstruct) {
            reverseInstruct = new BalanceAssetFluxInstruct();
            fillBalanceInstruct((BalanceAssetFluxInstruct) reverseInstruct, (BalanceAssetFluxInstruct) assetFluxInstruct);
        } else {
            reverseInstruct = new ExternalAssetFluxInstruct();
            fillExternalInstruct((ExternalAssetFluxInstruct) reverseInstruct, (ExternalAssetFluxInstruct) assetFluxInstruct);
        }
        reverseInstruct.setFluxInstructId(idGeneratorDomainService.genIdByRelateId(assetFluxInstruct.getFluxOrderId(), IdType.FLUX_INSTRUCT_ID));
        reverseInstruct.setInstructType(InstructType.REVERSE);
        reverseInstruct.setAmount(assetFluxInstruct.getAmount());
        reverseInstruct.setStatus(InstructStatus.INIT);
        reverseInstruct.setFundDetailId(assetFluxInstruct.getFundDetailId());
        reverseInstruct.setRelatedFluxInstructId(assetFluxInstruct.getFluxInstructId());
        return reverseInstruct;
    }

    private void fillBalanceInstruct(BalanceAssetFluxInstruct reverseInstruct, BalanceAssetFluxInstruct forwardInstruct) {
        reverseInstruct.setDebitAsset(forwardInstruct.getCreditAsset());
        reverseInstruct.setCreditAsset(forwardInstruct.getDebitAsset());
    }

    private void fillExternalInstruct(ExternalAssetFluxInstruct reverseInstruct, ExternalAssetFluxInstruct forwardInstruct) {
        reverseInstruct.setAssetInfo(forwardInstruct.getAssetInfo());
        reverseInstruct.setFundAction(forwardInstruct.getFundAction().reverse());
    }
}
