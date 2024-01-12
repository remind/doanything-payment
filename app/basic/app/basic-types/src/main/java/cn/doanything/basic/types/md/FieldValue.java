package cn.doanything.basic.types.md;

import lombok.Data;

/**
 * 字段值
 */
@Data
public class FieldValue {

    /**
     * 字段编码
     */
    private String fieldCode;

    /**
     * 值
     */
    private String value;
}
