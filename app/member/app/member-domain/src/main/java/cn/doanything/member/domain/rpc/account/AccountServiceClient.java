package cn.doanything.member.domain.rpc.account;

import java.util.List;

/**
 * @author wxj
 * 2024/1/4
 */
public interface AccountServiceClient {

    List<AccountDTO> createAccount(String memberId, List<String> accountTypes);
}
