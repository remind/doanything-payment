package cn.doanything.account.domain;

import cn.doanything.account.types.enums.AccountAttribute;
import cn.doanything.account.types.enums.AccountFamily;
import cn.doanything.commons.lang.types.Money;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wxj
 * 2023/12/16
 */
public class OuterAccount extends Account {

    /**
     * 会员ID
     */
    private String memberId;
    /**
     * 可用余额
     */
    private Money availableBalance = new Money(0);
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
     * 子账户信息
     */
    private Map<String, OuterSubAccount> subAccountMap = new HashMap<String, OuterSubAccount>();

    @Override
    public AccountFamily getAccountFamily() {
        return AccountFamily.OUTER;
    }
}
