package cn.doanything.basic.facade.md;

import cn.doanything.basic.types.md.RowValue;
import cn.doanything.basic.types.md.query.RowValueQuery;
import cn.doanything.commons.response.ResponseResult;
import cn.doanything.commons.response.page.PageResult;

import java.util.List;

/**
 * 数据项的值查询
 *
 * @author wxj
 * 2024/1/13
 */
public interface ItemValueQueryFacade {

    ResponseResult<List<RowValue>> loadAll(String itemCode);

    ResponseResult<RowValue> load(String itemCode, String primaryValue);

    ResponseResult<PageResult<RowValue>> pageQuery(RowValueQuery query, boolean fuzzy);
}
