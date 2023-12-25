package cn.doanything.account.infrastructure.persistence.convertor;

import cn.doanything.account.domain.detail.BufferedDetail;
import cn.doanything.account.infrastructure.convertor.EnumsConvertor;
import cn.doanything.account.infrastructure.persistence.dataobject.BufferedDetailDO;
import cn.doanything.commons.convertor.ReadWriteConvertor;
import cn.doanything.commons.lang.types.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author wxj
 * 2023/12/25
 */
@Mapper(componentModel = "spring", uses = {EnumsConvertor.class})
public interface BufferedDetailDalConvertor extends ReadWriteConvertor<BufferedDetail, BufferedDetailDO> {
    @Mapping(target = "amount", expression = "java(toMoney(bufferedDetailDO.getAmount(), bufferedDetailDO.getCurrencyCode()))")
    @Override
    BufferedDetail toEntity(BufferedDetailDO bufferedDetailDO);

    @Mapping(target = "currencyCode", expression = "java(bufferedDetail.getAmount().getCurrency().getCurrencyCode())")
    @Mapping(target = "amount", expression = "java(bufferedDetail.getAmount().getAmount())")
    @Override
    BufferedDetailDO toDo(BufferedDetail bufferedDetail);

    default Money toMoney(BigDecimal amount, String currencyCode) {
        return new Money(amount, Currency.getInstance(currencyCode));
    }
}
