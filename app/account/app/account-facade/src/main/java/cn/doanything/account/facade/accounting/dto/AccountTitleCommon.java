package cn.doanything.account.facade.accounting.dto;

import cn.doanything.account.types.accounting.AccountTitleRange;
import cn.doanything.account.types.accounting.AccountTitleType;
import cn.doanything.account.types.enums.BalanceDirection;
import lombok.Data;

/**
 * @author wxj
 * 2023/12/16
 */
@Data
public class AccountTitleCommon {

    /**
     * 科目号
     */
    private String titleCode;
    /**
     * 科目名称
     */
    private String titleName;

    /**
     * 父级科目号
     */
    private String parentTitleCode;

    /**
     * 科目类型
     */
    private AccountTitleType type;

    /**
     * 余额方向
     */
    private BalanceDirection balanceDirection;

    /**
     * 适用范围
     */
    private AccountTitleRange titleRange;
    /**
     * 备注
     */
    private String memo;
}
