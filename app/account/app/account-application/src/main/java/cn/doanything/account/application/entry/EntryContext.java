package cn.doanything.account.application.entry;

import cn.doanything.account.domain.AccountTransaction;
import cn.doanything.account.domain.detail.BufferedDetail;
import lombok.Data;

import java.util.List;

/**
 * 入账上下文
 * @author wxj
 * 2023/12/20
 */
@Data
public class EntryContext {

    /**
     * 入账事务
     */
    private AccountTransaction accountTransaction;

    /**
     * 分组操作明细
     */
    private List<AccountOperationGroup> accountOperationGroups;

    /**
     * 缓冲入账明细
     */
    private List<BufferedDetail> bufferedDetails;
}
