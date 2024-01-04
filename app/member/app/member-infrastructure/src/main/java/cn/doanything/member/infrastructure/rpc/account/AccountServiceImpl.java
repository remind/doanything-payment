package cn.doanything.member.infrastructure.rpc.account;

import cn.doanything.account.facade.manager.AccountManagerFacade;
import cn.doanything.account.facade.manager.dto.OuterAccountAddRequest;
import cn.doanything.account.facade.manager.dto.OuterAccountAddResponse;
import cn.doanything.commons.response.ResponseResult;
import cn.doanything.member.domain.rpc.account.AccountDTO;
import cn.doanything.member.domain.rpc.account.AccountService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 账户相关能力
 * @author wxj
 * 2024/1/4
 */
@Service
public class AccountServiceImpl implements AccountService {

    @DubboReference
    private AccountManagerFacade accountManagerFacade;

    @Override
    public List<AccountDTO> createAccount(String memberId, List<String> accountTypes) {
        List<OuterAccountAddRequest> requests = new ArrayList<>();
        accountTypes.forEach(accountType -> {
            OuterAccountAddRequest request = new OuterAccountAddRequest();
            request.setMemberId(memberId);
            request.setAccountType(accountType);
        });
        ResponseResult<List<OuterAccountAddResponse>> responseResult = accountManagerFacade.createOuterAccount(requests);
        List<AccountDTO> accountDTOS = new ArrayList<>();
        if (responseResult.isSuccess()) {
            responseResult.getData().forEach(response -> {
                AccountDTO accountDTO = new AccountDTO();
                accountDTO.setMemberId(response.getMemberId());
                accountDTO.setAccountType(response.getAccountType());
                accountDTO.setAccountNo(response.getAccountNo());
                accountDTOS.add(accountDTO);
            });
            return accountDTOS;
        }
        return null;
    }
}
