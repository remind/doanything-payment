package cn.doanything.account.infrastructure.persistence.convertor;

import cn.doanything.account.domain.buffer.BufferedRule;
import cn.doanything.account.infrastructure.convertor.EnumsConvertor;
import cn.doanything.account.infrastructure.persistence.dataobject.BufferedRuleDO;
import cn.doanything.commons.convertor.ReadWriteConvertor;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2023/12/25
 */
@Mapper(componentModel = "spring", uses = {EnumsConvertor.class})
public interface BufferedRuleDalConvertor extends ReadWriteConvertor<BufferedRule, BufferedRuleDO> {


}