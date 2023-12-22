package cn.doanything.account.facade.manager;

import cn.doanything.account.domain.Account;
import cn.doanything.account.domain.OuterAccountType;
import cn.doanything.account.domain.repository.AccountTypeRepository;
import cn.doanything.account.domain.repository.factory.AccountRepositoryFactory;
import cn.doanything.account.facade.manager.builder.AccountBuilder;
import cn.doanything.account.facade.manager.dto.OuterAccountAddRequest;
import cn.doanything.account.facade.manager.dto.OuterAccountResponse;
import cn.doanything.account.types.enums.AccountFamily;
import cn.doanything.commons.lang.SystemResultCode;
import cn.doanything.commons.lang.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wxj
 * 2023/12/22
 */
public class AccountManagerFacadeImpl implements AccountManagerFacade {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private AccountRepositoryFactory accountRepositoryFactory;

    @Autowired
    private AccountBuilder accountBuilder;

    @Override
    public OuterAccountResponse addOuterAccount(OuterAccountAddRequest request) {
        OuterAccountType outerAccountType = accountTypeRepository.load(request.getAccountType());
        AssertUtil.isNotNull(outerAccountType, SystemResultCode.ILLEGAL_PARAM, "账户类型不存在");
        Account account = accountBuilder.build(request);
        accountRepositoryFactory.getRepository(AccountFamily.OUTER).store(account);
        return null;
    }
}
