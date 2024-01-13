package cn.doanything.basic.infrastructure.persistence.md.convertor;

import cn.doanything.basic.types.md.RowValue;
import cn.doanything.basic.infrastructure.persistence.md.dataobject.ItemValueDO;
import cn.doanything.framework.dal.convertor.PageConvertor;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2024/1/13
 */
@Mapper(componentModel = "spring")
public interface ItemValueDalConvertor extends PageConvertor<RowValue, ItemValueDO> {

}
