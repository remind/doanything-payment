package cn.doanything.paycore.infrastructure.persistence.convertor;

import cn.doanything.commons.convertor.ReadWriteConvertor;
import cn.doanything.paycore.domain.flux.FluxOrder;
import cn.doanything.paycore.infrastructure.convertor.EnumsConvertor;
import cn.doanything.paycore.infrastructure.persistence.dataobject.FluxOrderDO;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2024/1/29
 */
@Mapper(componentModel = "spring", uses = {EnumsConvertor.class})
public interface FluxOrderDalConvertor extends ReadWriteConvertor<FluxOrder, FluxOrderDO> {


}
