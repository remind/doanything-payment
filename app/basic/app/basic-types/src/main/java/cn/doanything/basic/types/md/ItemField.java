package cn.doanything.basic.types.md;

import cn.doanything.commons.lang.Entity;
import lombok.Data;

/**
 * 数据项字段
 */
@Data
public class ItemField extends Entity {

    /**
     * 数据项编码
     */
    private String itemCode;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private DataType dataType;

    /**
     * 是否主键
     */
    private boolean primary;

    /**
     * 是否可空
     */
    private boolean nullable;

}
