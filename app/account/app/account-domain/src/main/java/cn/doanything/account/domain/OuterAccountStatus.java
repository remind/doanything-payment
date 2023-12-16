package cn.doanything.account.domain;

import cn.doanything.account.types.enums.ActivateStatus;
import cn.doanything.account.types.enums.DenyStatus;

/**
 * @author wxj
 * 2023/12/16
 */
public class OuterAccountStatus {

    /**
     * 激活状态
     */
    private ActivateStatus activeStatus;
    /**
     * 冻结状态
     */
    private DenyStatus denyStatus;
}
