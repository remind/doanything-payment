package cn.doanything.account.infrastructure.persistence.convertor;

/**
 * @author wxj
 * 2023/12/24
 */

import cn.doanything.account.domain.InnerAccount;
import cn.doanything.account.infrastructure.convertor.EnumsConvertor;
import cn.doanything.account.infrastructure.persistence.dataobject.InnerAccountDO;
import cn.doanything.commons.convertor.ReadWriteConvertor;
import cn.doanything.commons.lang.types.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.Currency;

@Mapper(componentModel = "spring", uses = {EnumsConvertor.class})
public interface InnerAccountDalConvertor extends ReadWriteConvertor<InnerAccount, InnerAccountDO> {

    @Mapping(target = "balance", expression = "java(toMoney(innerAccountDO.getBalance(), innerAccountDO.getCurrencyCode()))")
    @Override
    InnerAccount toEntity(InnerAccountDO innerAccountDO);

    @Mapping(target = "balance", expression = "java(account.getBalance().getAmount())")
    @Override
    InnerAccountDO toDo(InnerAccount account);

    default Money toMoney(BigDecimal amount, String currencyCode) {
        return new Money(amount, Currency.getInstance(currencyCode));
    }
}
