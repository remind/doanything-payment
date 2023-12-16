package cn.doanything.account.facade.accounting.convertor;

import cn.doanything.account.domain.accounting.AccountTitle;
import cn.doanything.account.facade.accounting.dto.AccountTitleAddRequest;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2023/12/16
 */
@Mapper(componentModel = "spring")
public interface AccountTitleConvertor {

    AccountTitle toEntity(AccountTitleAddRequest request);
}
