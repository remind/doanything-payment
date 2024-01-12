package cn.doanything.basic.domain.md;

import cn.doanything.basic.types.md.FieldValue;
import cn.doanything.commons.lang.Entity;
import lombok.Data;

import java.util.List;

/**
 * 数据项行值
 */
@Data
public class ColumnValue extends Entity {

    /**
     * 数据项编码
     */
    private String itemCode;

    /**
     * 主键值
     */
    private String primaryValue;

    /**
     * 各字段值
     */
    private List<FieldValue> fieldValues;
}
