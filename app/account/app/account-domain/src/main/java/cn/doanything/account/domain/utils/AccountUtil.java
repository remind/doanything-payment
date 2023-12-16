package cn.doanything.account.domain.utils;

import cn.doanything.account.types.enums.CrDr;
import cn.doanything.account.types.enums.IODirection;

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
}
