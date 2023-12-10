package cn.doanything.commons.lang.types;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;

/**
 * 金额类型，仅支持人民币
 * 币种：CNY
 * 舍入法：四舍五入
 *
 * 持续可考虑多币种、多舍入法支持
 * 开源参考：
 * https://github.com/JodaOrg/joda-money 比较重，对于单一币种没必要
 * @author remind
 * @version Money.java, v 0.1 2021年08月23日 8:45 下午 remind
 */
public class Money implements Serializable, Comparable<Money>, Cloneable {

    private static final long serialVersionUID = -4299855586085442979L;

    /**
     * 默认币种符号
     */
    public static final String DEFAULT_CURRENCY_SYMBOL = new String(new char[] { 0xffe5 });

    /**
     * 默认舍入法
     */
    public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;

    /**
     * 金额数字，元的数字
     */
    private BigDecimal amount;

    /**
     * 币种,人民币
     */
    private static final Currency currency = Currency.getInstance("CNY");

    /**
     * 默认为0元的金额
     */
    public Money() {
        this("0");
    }

    /**
     * 指定元的人民币金额
     * @param amount	金额
     */
    public Money(String amount) {
        this(new BigDecimal(amount));
    }

    /**
     * 指定元的人民币金额
     * @param amount	金额
     */
    public Money(double amount) {
        this(new BigDecimal(amount));
    }

    /**
     * 指定元的人民币金额
     * @param amount	金额
     */
    public Money(BigDecimal amount) {
        setAmount(amount);
    }

    /**
     * 获取金额
     * @return 金额
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * 设置货币
     * @param amount	金额
     */
    private void setAmount(BigDecimal amount) {
        if (amount != null)
            this.amount = amount.setScale(this.currency.getDefaultFractionDigits(), DEFAULT_ROUNDING_MODE);
    }

    /**
     * 获取分
     * @return	分
     */
    public long getCent() {
        return this.amount.multiply(getCentFactor()).longValue();
    }

    /**
     * 获取币种
     * @return	币种
     */
    public Currency getCurrency() {
        return this.currency;
    }

    /**
     * 换算成分的换算值
     * @return
     */
    public BigDecimal getCentFactor() {
        return (new BigDecimal(10)).pow(this.currency.getDefaultFractionDigits());
    }

    /**
     * 是否大于指定金额
     * @param other	对手金额
     * @return
     */
    public boolean greaterThan(Money other) {
        return (compareTo(other) > 0);
    }

    /**
     * 是否小于指定金额
     * @param other	对手金额
     * @return
     */
    public boolean lessThan(Money other) {
        return (compareTo(other) < 0);
    }

    /**
     * 加上指定金额，返回一个新对象
     * @param other	指定金额
     * @return
     */
    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    /**
     * 加上指定金额，返回原有对象
     * @param other	指定金额
     * @return
     */
    public Money addTo(Money other) {
        this.amount = this.amount.add(other.amount);
        return this;
    }

    /**
     * 减指定金额，返回一个新对象
     * @param other
     * @return
     */
    public Money subtract(Money other) {
        return new Money(this.amount.subtract(other.amount));
    }

    /**
     * 减指定金额，返回原有对象
     * @param other
     * @return
     */
    public Money subtractFrom(Money other) {
        this.amount = this.amount.subtract(other.amount);
        return this;
    }

    /**
     * 金额乘以指定数字，并返回新对象
     * @param val
     * @return
     */
    public Money multiply(long val) {
        return multiply(new BigDecimal(val));
    }

    /**
     * 金额乘以指定数字，并返回原有对象
     * @param val
     * @return
     */
    public Money multiplyBy(long val) {
        return multiplyBy(new BigDecimal(val));
    }

    /**
     * 金额乘以指定数字，并返回新对象
     * @param val
     * @return
     */
    public Money multiply(double val) {
        return multiply(new BigDecimal(val));
    }

    /**
     * 金额乘以指定数字，并返回原有对象
     * @param val
     * @return
     */
    public Money multiplyBy(double val) {
        return multiplyBy(new BigDecimal(val));
    }

    /**
     * 金额乘以指定数字，并返回新对象
     * @param val
     * @return
     */
    public Money multiply(BigDecimal val) {
        return new Money(this.amount.multiply(val));
    }

    /**
     * 金额乘以指定数字，并返回原有对象
     * @param val
     * @return
     */
    public Money multiplyBy(BigDecimal val) {
        setAmount(this.amount.multiply(val));
        return this;
    }

    /**
     * 金额除以指定数字，并返回新对象
     * @param val
     * @return
     */
    public Money divide(double val) {
        return divide(new BigDecimal(val));
    }

    /**
     * 金额除以指定数字，并返回原对象
     * @param val
     * @return
     */
    public Money divideBy(double val) {
        return divideBy(new BigDecimal(val));
    }

    /**
     * 金额除以指定数字，并返回新对象
     * @param val
     * @return
     */
    public Money divide(BigDecimal val) {
        return new Money(this.amount.divide(val));
    }

    /**
     * 金额除以指定数字，并返回原对象
     * @param val
     * @return
     */
    public Money divideBy(BigDecimal val) {
        setAmount(this.amount.divide(val));
        return this;
    }

    /**
     * 将金额分成指定份数，如12.77分成3份分别为：4.26、4.26、4.25
     * @param targets	分配份数
     * @return
     */
    public Money[] allocate(int targets) {
        long cent = getCent();
        Money[] results = new Money[targets];
        long lowResult = cent / targets;
        long highResult = lowResult + 1L;
        int remainder = (int) cent % targets;
        int i;
        for (i = 0; i < remainder; i++) {
            results[i] = new Money((new BigDecimal(highResult)).divide(getCentFactor()));
        }
        for (i = remainder; i < targets; i++) {
            results[i] = new Money((new BigDecimal(lowResult)).divide(getCentFactor()));
        }
        return results;
    }

    /**
     * 将金额按指定比率进行分配，如12.77按[1,2]分配，分配结果为[4.26,8.51]
     * @param ratios 分配比率
     * @return
     */
    public Money[] allocate(long[] ratios) {
        long[] results = new long[ratios.length];
        long total = 0L;
        for (int i = 0; i < ratios.length; i++)
            total += ratios[i];
        long remainder = getCent();
        int j;
        for (j = 0; j < results.length; j++) {
            results[j] = getCent() * ratios[j] / total;
            remainder -= results[j];
        }
        for (j = 0; j < remainder; j++)
            results[j] = results[j] + 1L;
        Money[] ret = new Money[ratios.length];
        for (int k = 0; k < ratios.length; k++)
            ret[k] = new Money((new BigDecimal(results[k])).divide(getCentFactor()));
        return ret;
    }

    /**
     * 带币种code格式化的金额字符串，如：CNY123,456.78
     * @return
     */
    public String formatWithCode() {
        NumberFormat nf = new DecimalFormat(getNumberFormatPattern());
        StringBuilder sb = new StringBuilder();
        sb.append(this.currency.getCurrencyCode()).append(nf.format(this.amount));
        return sb.toString();
    }

    /**
     * 格式化后的金额字符串，如：123,456.78
     * @return
     */
    public String format() {
        NumberFormat nf = new DecimalFormat(getNumberFormatPattern());
        return nf.format(this.amount);
    }

    /**
     * 带符号格式化后的金额，如：￥123,456.78
     * @return
     */
    public String formatWithSymbols() {
        NumberFormat nf = new DecimalFormat(DEFAULT_CURRENCY_SYMBOL + getNumberFormatPattern());
        nf.setCurrency(this.currency);
        return nf.format(this.amount);
    }

    /**
     * 格式化串
     * @return
     */
    private String getNumberFormatPattern() {
        int digits = this.currency.getDefaultFractionDigits();
        StringBuilder format = new StringBuilder();
        format.insert(0, '0');
        int i;
        for (i = 1; i < 19 - digits; i++) {
            if (i % 3 == 0) {
                format.insert(0, ',');
            }
            format.insert(0, '#');
        }
        if (digits > 0) {
            format.append('.');
            for (i = 0; i < digits; i++) {
                format.append("0");
            }
        }
        return format.toString();
    }

    @Override
    public String toString() {
        return getAmount().toString();
    }

    @Override
    public int compareTo(Money other) {
        return this.amount.compareTo(other.amount);
    }

    @Override
    public int hashCode() {
        long cent = this.amount.multiply(getCentFactor()).longValue();
        return (int) (cent ^ cent >>> 32L);
    }

    /**
     * 是否等于
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Money) {
            Money otherMoney = (Money) other;
            return this.amount.equals(otherMoney.amount);
        }
        return false;
    }

    @Override
    public Money clone() {
        return new Money(this.amount);
    }
}