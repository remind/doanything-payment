package cn.doanything.basic.infrastructure.persistence.md.convertor;

import cn.doanything.basic.types.md.DataItem;
import cn.doanything.basic.infrastructure.persistence.md.dataobject.DataItemDO;
import cn.doanything.framework.dal.convertor.PageConvertor;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2024/1/12
 */
@Mapper(componentModel = "spring")
public interface DataItemDalConvertor extends PageConvertor<DataItem, DataItemDO>  {
}
