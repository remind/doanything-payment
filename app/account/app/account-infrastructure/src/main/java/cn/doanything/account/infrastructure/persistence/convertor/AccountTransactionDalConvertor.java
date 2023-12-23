package cn.doanything.account.infrastructure.persistence.convertor;

import cn.doanything.account.domain.AccountTransaction;
import cn.doanything.account.infrastructure.persistence.dataobject.AccountTransactionDO;
import cn.doanything.commons.convertor.ReadWriteConvertor;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2023/12/23
 */
@Mapper(componentModel = "spring")
public interface AccountTransactionDalConvertor extends ReadWriteConvertor<AccountTransaction, AccountTransactionDO> {
}
