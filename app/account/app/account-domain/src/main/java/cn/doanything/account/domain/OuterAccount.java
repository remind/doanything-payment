package cn.doanything.account.domain;

import cn.doanything.account.types.enums.AccountAttribute;
import cn.doanything.account.types.enums.AccountFamily;
import cn.doanything.commons.lang.types.Money;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxj
 * 2023/12/16
 */
@Data
public class OuterAccount extends Account {

    /**
     * 会员ID
     */
    private String memberId;
    /**
     * 可用余额
     */
    private Money availableBalance = new Money();
    /**
     * 账户属性
     */
    private AccountAttribute accountAttribute;
    /**
     * 账户类型
     */
    private Integer accountType;

    /**
     * 账户状态
     */
    private OuterAccountStatus status;

    /**
     * 子账户，一个资金类型的只能有一个
     */
    private List<OuterSubAccount> outerSubAccounts = new ArrayList<>();


    @Override
    public AccountFamily getAccountFamily() {
        return AccountFamily.OUTER;
    }

    @Override
    public Money getBalance() {
        Money balance = new Money();
        outerSubAccounts.forEach(outerSubAccount -> balance.addTo(outerSubAccount.getBalance()));
        return balance;
    }

    public Money getAvailableBalance() {
        Money balance = new Money();
        outerSubAccounts.forEach(outerSubAccount -> balance.addTo(outerSubAccount.getAvailableBalance()));
        return balance;
    }
}
