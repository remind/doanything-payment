package cn.doanything.commons.convertor;

import cn.doanything.commons.lang.types.Money;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * 基础表达式转换器
 * @author wxj
 * 2024/1/19
 */
public interface BaseExpressionConvertor {

    default Money toMoney(BigDecimal amount, String currencyCode) {
        return new Money(amount, Currency.getInstance(currencyCode));
    }
    default String toCurrencyCode(Money money) {
        return money != null ? money.getCurrency().getCurrencyCode() : "";
    }
    default BigDecimal toAmountValue(Money money) {
        return money != null ? money.getAmount() : BigDecimal.ZERO;
    }
}
