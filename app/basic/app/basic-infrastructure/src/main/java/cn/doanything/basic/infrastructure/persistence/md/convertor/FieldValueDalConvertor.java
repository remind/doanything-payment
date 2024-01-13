package cn.doanything.basic.infrastructure.persistence.md.convertor;

import cn.doanything.basic.infrastructure.persistence.md.dataobject.FieldValueDO;
import cn.doanything.basic.types.md.FieldValue;
import cn.doanything.basic.types.md.RowValue;
import cn.doanything.commons.convertor.ReadConvertor;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2024/1/13
 */
@Mapper(componentModel = "spring")
public interface FieldValueDalConvertor extends ReadConvertor<FieldValue, FieldValueDO> {

     FieldValueDO toDo(RowValue rowValue, FieldValue fieldValue);

}
