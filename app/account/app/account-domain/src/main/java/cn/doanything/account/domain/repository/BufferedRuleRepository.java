package cn.doanything.account.domain.repository;

import cn.doanything.account.domain.buffer.BufferedRule;
import cn.doanything.account.types.enums.CrDr;

/**
 * @author wxj
 * 2023/12/25
 */
public interface BufferedRuleRepository {
    void store(BufferedRule rule);

    void reStore(BufferedRule rule);

    boolean isExists(String accountNo, CrDr crDr);

}
