package cn.doanything.account.facade.manager;

import cn.doanything.account.facade.manager.dto.InnerAccountAddRequest;
import cn.doanything.account.facade.manager.dto.OuterAccountAddRequest;
import cn.doanything.commons.response.ResponseResult;

/**
 * 账户管理相关
 * @author wxj
 * 2023/12/22
 */
public interface AccountManagerFacade {

    /**
     * 创建外部账户
     * @param request
     * @return
     */
    ResponseResult<String> createOuterAccount(OuterAccountAddRequest request);

    /**
     * 创建内部账户
     * @param request
     * @return
     */
    ResponseResult<String> createInnerAccount(InnerAccountAddRequest request);
}
