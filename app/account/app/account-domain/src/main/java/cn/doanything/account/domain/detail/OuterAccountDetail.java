package cn.doanything.account.domain.detail;

import cn.doanything.account.types.enums.IODirection;
import cn.doanything.commons.lang.types.Money;
import lombok.Data;

import java.util.List;

/**
 * @author wxj
 * 2023/12/16
 */
@Data
public class OuterAccountDetail extends AccountDetail {

    /**
     * 资金类型
     */
    private String fundType;

    /**
     * 入账前余额
     */
    private Money beforeBalance = new Money();

    /**
     * 入账后余额
     */
    private Money afterBalance = new Money();

    /**
     * 加减方向
     */
    private IODirection ioDirection;

    /**
     * 子户明细
     */
    private List<OuterSubAccountDetail> outerSubAccountDetails;
}
