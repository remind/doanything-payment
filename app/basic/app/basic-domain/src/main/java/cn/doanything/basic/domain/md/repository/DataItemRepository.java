package cn.doanything.basic.domain.md.repository;

import cn.doanything.basic.domain.md.DataItem;
import cn.doanything.basic.types.md.ItemField;
import cn.doanything.basic.types.md.query.DataItemQuery;
import cn.doanything.commons.response.page.PageResult;
import cn.doanything.commons.response.page.Paging;

/**
 * @author wxj
 * 2024/1/12
 */
public interface DataItemRepository {

    DataItem load(String code);

    void store(DataItem dataItem);

    void reStore(DataItem dataItem);

    /**
     * 新增字段
     * @param itemField
     */
    void addItemField(ItemField itemField);

    /**
     * 修改字段
     * @param itemField
     */
    void updateItemField(ItemField itemField);

    void deleteItemField(String itemCode, String fieldCode);

    PageResult<DataItem> pageQuery(DataItemQuery query, Paging paging);
}
