package cn.doanything.account.facade.manager.convertor;

import cn.doanything.account.domain.InnerAccount;
import cn.doanything.account.facade.manager.dto.InnerAccountAddRequest;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2023/12/23
 */
@Mapper(componentModel = "spring")
public interface InnerAccountConvertor {

    InnerAccount toOuterAccount(InnerAccountAddRequest request);
}
