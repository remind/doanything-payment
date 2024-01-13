package cn.doanything.basic.types.md.query;

import cn.doanything.commons.response.page.PageQuery;
import lombok.Data;

/**
 * @author wxj
 * 2024/1/12
 */
@Data
public class DataItemQuery extends PageQuery {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;
}
