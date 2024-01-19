package cn.doanything.account.facade.manager;

import cn.doanything.account.domain.InnerAccount;
import cn.doanything.account.domain.OuterAccount;
import cn.doanything.account.domain.repository.AccountTypeRepository;
import cn.doanything.account.domain.repository.InnerAccountRepository;
import cn.doanything.account.domain.repository.OuterAccountRepository;
import cn.doanything.account.facade.manager.builder.InnerAccountBuilder;
import cn.doanything.account.facade.manager.builder.OuterAccountBuilder;
import cn.doanything.account.facade.manager.dto.InnerAccountAddRequest;
import cn.doanything.account.facade.manager.dto.OuterAccountAddRequest;
import cn.doanything.account.facade.manager.dto.OuterAccountAddResponse;
import cn.doanything.commons.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

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
    private OuterAccountBuilder outerAccountBuilder;

    @Autowired
    private InnerAccountBuilder innerAccountBuilder;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private InnerAccountRepository innerAccountRepository;

    @Autowired
    private OuterAccountRepository outerAccountRepository;

    @Override
    public ResponseResult<String> createOuterAccount(OuterAccountAddRequest request) {
        try {
            OuterAccount account = outerAccountBuilder.build(request);
            String accountNo = transactionTemplate.execute(status -> outerAccountRepository.store(account));
            return ResponseResult.success(accountNo);
        } catch (Exception e) {
            log.error("外部户开户失败,memberId="+ request.getMemberId(), e);
            return ResponseResult.fail(e.getMessage());
        }
    }

    @Override
    public ResponseResult<List<OuterAccountAddResponse>> createOuterAccount(List<OuterAccountAddRequest> requests) {
        List<OuterAccount> outerAccounts = outerAccountBuilder.build(requests);
        List<OuterAccountAddResponse> responses = new ArrayList<>();
        transactionTemplate.executeWithoutResult(status -> {
            for (OuterAccount outerAccount : outerAccounts) {
                String accountNo = outerAccountRepository.store(outerAccount);
                responses.add(new OuterAccountAddResponse(accountNo, outerAccount.getAccountType(), outerAccount.getAccountNo()));
            }
        });
        return ResponseResult.success(responses);
    }

    @Override
    public ResponseResult<String> createInnerAccount(InnerAccountAddRequest request) {
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
