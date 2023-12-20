package cn.doanything.account.facade;

import cn.doanything.account.facade.dto.AccountingRequest;

/**
 * 账务相关接口
 * @author wxj
 * 2023/12/20
 */
public interface AccountFacade {

    /**
     * 入账申请
     * @param accountingRequest 请求参数
     */
    void apply(AccountingRequest accountingRequest);
}
