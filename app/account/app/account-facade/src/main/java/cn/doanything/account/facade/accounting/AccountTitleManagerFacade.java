package cn.doanything.account.facade.accounting;

import cn.doanything.account.facade.accounting.dto.AccountTitleAddRequest;
import cn.doanything.commons.response.ResponseResult;

/**
 * @author wxj
 * 2023/12/16
 */
public interface AccountTitleManagerFacade {

    /**
     * 创建科目
     * @param request
     * @return
     */
    ResponseResult<String> createAccountTitle(AccountTitleAddRequest request);

    /**
     * 更新科目
     * @param request
     * @return
     */
    ResponseResult<String> updateAccountTitle(AccountTitleAddRequest request);
}
