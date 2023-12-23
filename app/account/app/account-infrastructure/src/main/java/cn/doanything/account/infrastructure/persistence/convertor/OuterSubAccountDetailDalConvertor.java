package cn.doanything.account.infrastructure.persistence.convertor;

import cn.doanything.account.domain.detail.OuterSubAccountDetail;
import cn.doanything.account.infrastructure.convertor.EnumsConvertor;
import cn.doanything.account.infrastructure.persistence.dataobject.OuterSubAccountDetailDO;
import cn.doanything.commons.convertor.ReadWriteConvertor;
import cn.doanything.commons.lang.types.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author wxj
 * 2023/12/23
 */
@Mapper(componentModel = "spring", uses = {EnumsConvertor.class})
public interface OuterSubAccountDetailDalConvertor extends ReadWriteConvertor<OuterSubAccountDetail, OuterSubAccountDetailDO> {
    @Mapping(target = "beforeBalance", expression = "java(toMoney(outerSubAccountDetailDO.getBeforeBalance(), outerSubAccountDetailDO.getCurrencyCode()))")
    @Mapping(target = "afterBalance", expression = "java(toMoney(outerSubAccountDetailDO.getAfterBalance(), outerSubAccountDetailDO.getCurrencyCode()))")
    @Mapping(target = "amount", expression = "java(toMoney(outerSubAccountDetailDO.getAmount(), outerSubAccountDetailDO.getCurrencyCode()))")
    @Override
    OuterSubAccountDetail toEntity(OuterSubAccountDetailDO outerSubAccountDetailDO);

    @Mapping(target = "currencyCode", expression = "java(outerSubAccountDetail.getBeforeBalance().getCurrency().getCurrencyCode())")
    @Mapping(target = "beforeBalance", expression = "java(outerSubAccountDetail.getBeforeBalance().getAmount())")
    @Mapping(target = "afterBalance", expression = "java(outerSubAccountDetail.getAfterBalance().getAmount())")
    @Mapping(target = "amount", expression = "java(outerSubAccountDetail.getAmount().getAmount())")
    @Override
    OuterSubAccountDetailDO toDo(OuterSubAccountDetail outerSubAccountDetail);

    default Money toMoney(BigDecimal amount, String currencyCode) {
        return new Money(amount, Currency.getInstance(currencyCode));
    }
}
