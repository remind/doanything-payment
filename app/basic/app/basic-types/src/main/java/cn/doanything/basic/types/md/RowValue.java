package cn.doanything.basic.types.md;

import cn.doanything.commons.lang.Entity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据项行值
 */
@Data
public class RowValue extends Entity {

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
    private List<FieldValue> fieldValues = new ArrayList<>();

    /**
     * 获取指定字段值
     * @param fieldCode
     * @return
     */
    public String getFieldValue(String fieldCode) {
        for (FieldValue fieldValue : fieldValues) {
            if (fieldValue.getFieldCode().equals(fieldCode)) {
                return fieldValue.getValue();
            }
        }
        return null;
    }
}
