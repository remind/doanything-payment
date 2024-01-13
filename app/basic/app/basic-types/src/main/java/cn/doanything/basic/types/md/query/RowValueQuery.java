package cn.doanything.basic.types.md.query;

import cn.doanything.commons.response.page.PageQuery;
import lombok.Data;

/**
 * 数据分页查询
 * @author wxj
 * 2024/1/13
 */
@Data
public class RowValueQuery extends PageQuery {

    /**
     * 数据项编码
     */
    private String itemCode;

    /**
     * 主键值
     */
    private String primaryValue;
}
