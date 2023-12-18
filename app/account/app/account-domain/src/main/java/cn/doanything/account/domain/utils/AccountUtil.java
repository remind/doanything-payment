package cn.doanything.account.domain.utils;

import cn.doanything.account.types.enums.CrDr;
import cn.doanything.account.types.enums.IODirection;
import cn.doanything.commons.lang.types.Money;

/**
 * @author wxj
 * 2023/12/16
 */
public class AccountUtil {

    /**
     * 根据账户方向和明细方向计算加减方向
     * @param accountDirection
     * @param detailDirection
     * @return
     */
    public static IODirection convert(CrDr accountDirection, CrDr detailDirection) {
        return accountDirection == detailDirection ? IODirection.IN : IODirection.OUT;
    }

    /**
     * 更新余额
     * @param balance
     * @param ioDirection
     * @param amount
     */
    public static Money changeBalance(Money balance, IODirection ioDirection, Money amount) {
        switch (ioDirection) {
            case IN:
                return balance.add(amount);
            case OUT:
                return balance.subtract(amount);
            default:
                throw new RuntimeException("出入方向错误");
        }
    }
}
