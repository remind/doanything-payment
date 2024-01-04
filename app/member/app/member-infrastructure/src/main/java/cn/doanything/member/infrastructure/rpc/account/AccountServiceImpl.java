package cn.doanything.member.infrastructure.rpc.account;

import cn.doanything.account.facade.manager.AccountManagerFacade;
import cn.doanything.member.domain.rpc.account.AccountDTO;
import cn.doanything.member.domain.rpc.account.AccountService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wxj
 * 2024/1/4
 */
@Service
public class AccountServiceImpl implements AccountService {

    @DubboReference
    private AccountManagerFacade accountManagerFacade;
    @Override
    public List<AccountDTO> createAccount(String memberId, List<String> accountType) {
        return null;
    }
}
