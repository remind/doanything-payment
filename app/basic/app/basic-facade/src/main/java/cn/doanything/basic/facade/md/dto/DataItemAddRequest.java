package cn.doanything.basic.facade.md.dto;

import cn.doanything.basic.types.md.ItemField;
import lombok.Data;

import java.util.List;

/**
 * @author wxj
 * 2024/1/12
 */
@Data
public class DataItemAddRequest {

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
