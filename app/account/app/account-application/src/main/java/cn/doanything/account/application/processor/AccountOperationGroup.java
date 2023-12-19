package cn.doanything.account.application.processor;

import cn.doanything.account.domain.detail.AccountDetail;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxj
 * 2023/12/16
 */
@Data
public class AccountOperationGroup implements Comparable<AccountOperationGroup> {
    /**
     * 帐号
     */
    private String accountNo;

    /**
     * 账户余额明细
     */
    private List<AccountDetail> details = new ArrayList<>();

    @Override
    public int compareTo(AccountOperationGroup accountOperationGroup) {
        if (accountNo.length() == accountOperationGroup.getAccountNo().length()) {
            return this.accountNo.compareTo(accountOperationGroup.getAccountNo());
        } else if (accountNo.length() > accountOperationGroup.getAccountNo().length()) {
            return 1;
        } else {
            return -1;
        }
    }
}