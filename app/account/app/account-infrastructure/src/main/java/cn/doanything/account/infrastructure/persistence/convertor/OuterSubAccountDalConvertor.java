package cn.doanything.account.infrastructure.persistence.convertor;

import cn.doanything.account.domain.OuterSubAccount;
import cn.doanything.account.infrastructure.persistence.dataobject.OuterSubAccountDO;
import cn.doanything.commons.lang.types.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

/**
 * @author wxj
 * 2023/12/18
 */
@Mapper(componentModel = "spring")
public interface OuterSubAccountDalConvertor  {

    OuterSubAccountDalConvertor INSTANCE = Mappers.getMapper(OuterSubAccountDalConvertor.class);

    @Mapping(target = "availableBalance", expression = "java(toMoney(outerSubAccountDO.getAvailableBalance(), outerSubAccountDO.getCurrencyCode()))")
    @Mapping(target = "balance", expression = "java(toMoney(outerSubAccountDO.getBalance(), outerSubAccountDO.getCurrencyCode()))")
    OuterSubAccount toEntity(OuterSubAccountDO outerSubAccountDO);

    default Money toMoney(BigDecimal amount, String currencyCode) {
        return new Money(amount, currencyCode);
    }

}

