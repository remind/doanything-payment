package cn.doanything.account.infrastructure.persistence.convertor;


import cn.doanything.account.domain.accounting.AccountTitle;
import cn.doanything.account.infrastructure.convertor.EnumsConvertor;
import cn.doanything.account.infrastructure.persistence.dataobject.AccountTitleDO;
import cn.doanything.commons.convertor.ReadWriteConvertor;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2023/12/10
 */
@Mapper(componentModel = "spring", uses = {EnumsConvertor.class})
public interface AccountTitleDalConvertor extends ReadWriteConvertor<AccountTitle, AccountTitleDO> {

}
