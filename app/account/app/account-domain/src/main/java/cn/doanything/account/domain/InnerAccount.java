package cn.doanything.account.domain;

import cn.doanything.account.types.enums.AccountFamily;

/**
 * @author wxj
 * 2023/12/16
 */
public class InnerAccount extends Account {
    @Override
    public AccountFamily getAccountFamily() {
        return AccountFamily.INNER;
    }
}
