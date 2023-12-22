package cn.doanything.account.facade.manager.builder;

import cn.doanything.account.domain.Account;
import cn.doanything.account.facade.manager.dto.OuterAccountAddRequest;

/**
 * @author wxj
 * 2023/12/22
 */
public interface AccountBuilder {

    Account build(OuterAccountAddRequest request);
}
