package cn.doanything.commons.lang.utils;

import org.junit.Test;

import java.util.Currency;
import java.util.Locale;

/**
 * @author wxj
 * 2023/12/20
 */
public class CurrencyTest {

    @Test
    public void info() {

        Currency.getAvailableCurrencies().forEach(CurrencyTest::print);
    }

    public static void print(Currency currency) {
        System.out.println(currency.getCurrencyCode() + "\t"
                + currency.getSymbol() + "\t"
                + currency.getSymbol(Locale.US) + "\t"
                + currency.getDisplayName() + "\t"
                + currency.getDefaultFractionDigits() + "\t"
                + currency.getNumericCodeAsString());
    }
}
