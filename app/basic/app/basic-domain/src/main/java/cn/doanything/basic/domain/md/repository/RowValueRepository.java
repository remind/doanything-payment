package cn.doanything.basic.domain.md.repository;

import cn.doanything.basic.types.md.RowValue;
import cn.doanything.basic.types.md.query.RowValueQuery;
import cn.doanything.commons.response.page.PageResult;

import java.util.List;

/**
 * @author wxj
 * 2024/1/12
 */
public interface RowValueRepository {

    /**
     * 分页查询
     * @param query 查询条件
     * @param fuzzy 是否模糊查询
     * @return
     */
    PageResult<RowValue> pageQuery(RowValueQuery query, boolean fuzzy);

    /**
     * 加载数据
     * @param itemCode  数据项编码
     * @param primaryValue  主键值
     * @return
     */
    RowValue load(String itemCode, String primaryValue);


    /**
     * 查询某个数据项所有
     * @param itemCode
     * @return
     */
    List<RowValue> loadAll(String itemCode);

    /**
     * 根据非主键字段查询，没有索引，数据量过大会比较慢
     * @param itemCode
     * @param filedCode
     * @param fieldValue
     * @return
     */
    List<RowValue> loadByFiledValue(String itemCode, String filedCode, String fieldValue);

    void store(RowValue rowValue);

    boolean reStore(RowValue rowValue);

    boolean remove(String itemCode, String primaryValue);

}
