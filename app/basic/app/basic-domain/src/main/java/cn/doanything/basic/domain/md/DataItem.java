package cn.doanything.basic.domain.md;

import cn.doanything.basic.types.md.ItemField;
import cn.doanything.commons.lang.Entity;
import lombok.Data;

import java.util.List;

/**
 * 数据项
 */
@Data
public class DataItem extends Entity {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String memo;

    /**
     * 字段
     */
    private List<ItemField> itemFields;
}
