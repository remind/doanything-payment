package cn.doanything.account.facade.manager;

import cn.doanything.account.facade.manager.dto.OuterAccountAddRequest;
import cn.doanything.account.facade.manager.dto.OuterAccountResponse;

/**
 * 账户管理相关
 * @author wxj
 * 2023/12/22
 */
public interface AccountManagerFacade {

    OuterAccountResponse addOuterAccount(OuterAccountAddRequest request);
}
