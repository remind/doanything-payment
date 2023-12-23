package cn.doanything.account.facade.manager;

import cn.doanything.account.domain.Account;
import cn.doanything.account.domain.OuterAccountType;
import cn.doanything.account.domain.repository.AccountTypeRepository;
import cn.doanything.account.domain.repository.factory.AccountRepositoryFactory;
import cn.doanything.account.facade.manager.builder.AccountBuilder;
import cn.doanything.account.facade.manager.builder.impl.OuterAccountBuilder;
import cn.doanything.account.facade.manager.dto.OuterAccountAddRequest;
import cn.doanything.account.types.enums.AccountFamily;
import cn.doanything.commons.response.GlobalResultCode;
import cn.doanything.commons.lang.utils.AssertUtil;
import cn.doanything.commons.response.ResponseResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author wxj
 * 2023/12/22
 */
@Service
@Slf4j
public class AccountManagerFacadeImpl implements AccountManagerFacade {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private AccountRepositoryFactory accountRepositoryFactory;

    @Resource(type = OuterAccountBuilder.class)
    private AccountBuilder outerAccountBuilder;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public ResponseResult addOuterAccount(OuterAccountAddRequest request) {
        try {
            OuterAccountType outerAccountType = accountTypeRepository.load(request.getAccountType());
            AssertUtil.isNotNull(outerAccountType, GlobalResultCode.ILLEGAL_PARAM, "账户类型不存在");
            Account account = outerAccountBuilder.build(request);
            String accountNo = transactionTemplate.execute(status -> accountRepositoryFactory.getRepository(AccountFamily.OUTER).store(account));
            return ResponseResult.success(accountNo);
        } catch (Exception e) {
            log.error("开户失败,memberId="+ request.getMemberId(), e);
            return ResponseResult.fail(e.getMessage());
        }
    }
}
