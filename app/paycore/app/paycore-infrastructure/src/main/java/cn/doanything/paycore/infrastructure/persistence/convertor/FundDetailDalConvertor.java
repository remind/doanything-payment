package cn.doanything.paycore.infrastructure.persistence.convertor;

import cn.doanything.commons.convertor.ReadWriteConvertor;
import cn.doanything.paycore.infrastructure.convertor.EnumsConvertor;
import cn.doanything.paycore.infrastructure.persistence.dataobject.FundDetailDO;
import cn.doanything.paycore.types.asset.AssetType;
import cn.doanything.paycore.types.asset.BalanceAsset;
import cn.doanything.paycore.types.funds.FundDetail;
import cn.hutool.json.JSONUtil;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author wxj
 * 2024/1/17
 */
@Mapper(componentModel = "spring", uses = {EnumsConvertor.class})
public interface FundDetailDalConvertor extends ReadWriteConvertor<FundDetail, FundDetailDO> {


    @Override
    @Mapping(target = "amount", expression = "java(toMoney(fundDetailDO.getAmount(), fundDetailDO.getCurrencyCode()))")
    @Mapping(target = "assetInfo", ignore = true)
    FundDetail toEntity(FundDetailDO fundDetailDO);

    @AfterMapping
    default void fillEntityAsset(@MappingTarget FundDetail fundDetail, FundDetailDO fundDetailDO) {
        if (fundDetailDO.getAssetType().equals(AssetType.BALANCE.getCode())) {
            fundDetail.setAssetInfo(JSONUtil.toBean(fundDetailDO.getAssetInfo(), BalanceAsset.class));
        }
    }

    @Override
    @Mapping(target = "amount", expression = "java(toAmountValue(fundDetail.getAmount()))")
    @Mapping(target = "currencyCode", expression = "java(toCurrencyCode(fundDetail.getAmount()))")
    @Mapping(target = "assetInfo", ignore = true)
    FundDetailDO toDo(FundDetail fundDetail);

    @AfterMapping
    default void fillDOAsset(FundDetail fundDetail, @MappingTarget FundDetailDO fundDetailDO) {
        fundDetailDO.setAction(fundDetail.getFundAction().getCode());
        fundDetailDO.setAssetInfo(JSONUtil.toJsonStr(fundDetail.getAssetInfo()));
    }

}
