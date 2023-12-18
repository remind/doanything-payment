package cn.doanything.account.domain;

import cn.doanything.account.types.enums.AccountFamily;
import cn.doanything.commons.lang.types.Money;
import lombok.Setter;

/**
 * @author wxj
 * 2023/12/16
 */
public class InnerAccount extends Account {

    private Money balance = new Money();
    @Override
    public AccountFamily getAccountFamily() {
        return AccountFamily.INNER;
    }

    @Override
    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }
}
