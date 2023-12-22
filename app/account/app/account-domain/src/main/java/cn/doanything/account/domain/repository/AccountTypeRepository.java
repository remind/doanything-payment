package cn.doanything.account.domain.repository;

import cn.doanything.account.domain.OuterAccountType;

/**
 * @author wxj
 * 2023/12/22
 */
public interface AccountTypeRepository {

    OuterAccountType load(String code);
}
