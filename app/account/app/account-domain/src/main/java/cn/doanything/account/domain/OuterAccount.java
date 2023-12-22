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
    private String accountType;

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

    /**
     * 根据资金类型获取子账户
     *
     * @param fundType
     * @return
     */
    public OuterSubAccount getSubAccountByFundType(String fundType) {
        return outerSubAccounts.stream().filter(outerSubAccount ->
                outerSubAccount.getFundType().equals(fundType)
        ).findFirst().orElse(null);
    }

    /**
     * 新增子账户
     * @param fundType
     * @return
     */
    public OuterSubAccount addSubAccount(String fundType) {
        OuterSubAccount outerSubAccount = new OuterSubAccount();
        outerSubAccount.setAccountNo(this.accountNo);
        outerSubAccount.setFundType(fundType);
        this.outerSubAccounts.add(outerSubAccount);
        return outerSubAccount;
    }

    @Override
    public void setAccountNo(String accountNo) {
        super.setAccountNo(accountNo);
        outerSubAccounts.forEach(outerSubAccount -> outerSubAccount.setAccountNo(accountNo));
    }
}
