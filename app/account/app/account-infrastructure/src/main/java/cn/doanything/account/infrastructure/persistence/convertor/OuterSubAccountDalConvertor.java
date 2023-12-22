package cn.doanything.account.infrastructure.persistence.convertor;

import cn.doanything.account.domain.OuterSubAccount;
import cn.doanything.account.infrastructure.persistence.dataobject.OuterSubAccountDO;
import cn.doanything.commons.convertor.ReadWriteConvertor;
import cn.doanything.commons.lang.types.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author wxj
 * 2023/12/18
 */
@Mapper(componentModel = "spring")
public interface OuterSubAccountDalConvertor  extends ReadWriteConvertor<OuterSubAccount, OuterSubAccountDO> {

    OuterSubAccountDalConvertor INSTANCE = Mappers.getMapper(OuterSubAccountDalConvertor.class);

    @Mapping(target = "availableBalance", expression = "java(toMoney(outerSubAccountDO.getAvailableBalance(), outerSubAccountDO.getCurrencyCode()))")
    @Mapping(target = "balance", expression = "java(toMoney(outerSubAccountDO.getBalance(), outerSubAccountDO.getCurrencyCode()))")
    @Override
    OuterSubAccount toEntity(OuterSubAccountDO outerSubAccountDO);

    @Mapping(target = "balance", expression = "java(outerSubAccount.getBalance().getAmount())")
    @Mapping(target = "availableBalance", expression = "java(outerSubAccount.getAvailableBalance().getAmount())")
    @Override
    OuterSubAccountDO toDo(OuterSubAccount outerSubAccount);

    default Money toMoney(BigDecimal amount, String currencyCode) {
        return new Money(amount, Currency.getInstance(currencyCode));
    }

}

