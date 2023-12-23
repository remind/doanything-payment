package cn.doanything.account.facade.manager;

import cn.doanything.account.domain.Account;
import cn.doanything.account.domain.InnerAccount;
import cn.doanything.account.domain.OuterAccountType;
import cn.doanything.account.domain.repository.AccountTypeRepository;
import cn.doanything.account.domain.repository.InnerAccountRepository;
import cn.doanything.account.domain.repository.factory.AccountRepositoryFactory;
import cn.doanything.account.facade.manager.builder.InnerAccountBuilder;
import cn.doanything.account.facade.manager.builder.OuterAccountBuilder;
import cn.doanything.account.facade.manager.dto.InnerAccountAddRequest;
import cn.doanything.account.facade.manager.dto.OuterAccountAddRequest;
import cn.doanything.account.types.enums.AccountFamily;
import cn.doanything.commons.response.GlobalResultCode;
import cn.doanything.commons.lang.utils.AssertUtil;
import cn.doanything.commons.response.ResponseResult;
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

    @Autowired
    private OuterAccountBuilder outerAccountBuilder;

    @Autowired
    private InnerAccountBuilder innerAccountBuilder;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private InnerAccountRepository innerAccountRepository;

    @Override
    public ResponseResult<String> addOuterAccount(OuterAccountAddRequest request) {
        try {
            OuterAccountType outerAccountType = accountTypeRepository.load(request.getAccountType());
            AssertUtil.isNotNull(outerAccountType, GlobalResultCode.ILLEGAL_PARAM, "账户类型不存在");
            Account account = outerAccountBuilder.build(request);
            String accountNo = transactionTemplate.execute(status -> accountRepositoryFactory.getRepository(AccountFamily.OUTER).store(account));
            return ResponseResult.success(accountNo);
        } catch (Exception e) {
            log.error("外部户开户失败,memberId="+ request.getMemberId(), e);
            return ResponseResult.fail(e.getMessage());
        }
    }

    @Override
    public ResponseResult<String> addInnerAccount(InnerAccountAddRequest request) {
        try {
            InnerAccount account = innerAccountBuilder.build(request);
            String accountNo = transactionTemplate.execute(status -> innerAccountRepository.store(account));
            return ResponseResult.success(accountNo);
        } catch (Exception e) {
            log.error("内部户开户失败,titleCode="+ request.getTitleCode(), e);
            return ResponseResult.fail(e.getMessage());
        }
    }
}
