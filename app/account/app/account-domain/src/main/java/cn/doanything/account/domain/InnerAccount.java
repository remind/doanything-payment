package cn.doanything.account.domain;

import cn.doanything.account.domain.utils.AccountUtil;
import cn.doanything.account.types.enums.AccountFamily;
import cn.doanything.account.types.enums.IODirection;
import cn.doanything.commons.lang.types.Money;
import lombok.Data;
import lombok.Setter;

/**
 * @author wxj
 * 2023/12/16
 */
@Data
public class InnerAccount extends Account {

    /**
     * 余额
     */
    private Money balance = new Money();

    /**
     * 备注
     */
    private String memo;

    @Override
    public AccountFamily getAccountFamily() {
        return AccountFamily.INNER;
    }

    public void updateBalance(IODirection ioDirection, Money amount) {
        this.balance = AccountUtil.changeBalance(this.balance, ioDirection, amount);
    }
}
