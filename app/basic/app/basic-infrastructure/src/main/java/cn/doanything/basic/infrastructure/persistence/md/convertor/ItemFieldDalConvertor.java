package cn.doanything.basic.infrastructure.persistence.md.convertor;

import cn.doanything.basic.infrastructure.persistence.md.dataobject.ItemFieldDO;
import cn.doanything.basic.types.md.ItemField;
import cn.doanything.commons.convertor.ReadWriteConvertor;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2024/1/12
 */
@Mapper(componentModel = "spring")
public interface ItemFieldDalConvertor extends ReadWriteConvertor<ItemField, ItemFieldDO> {
}
