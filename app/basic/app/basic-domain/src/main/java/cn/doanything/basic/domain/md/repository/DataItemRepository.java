package cn.doanything.basic.domain.md.repository;

import cn.doanything.basic.types.md.DataItem;
import cn.doanything.basic.types.md.ItemField;
import cn.doanything.basic.types.md.query.DataItemQuery;
import cn.doanything.commons.response.page.PageResult;

/**
 * @author wxj
 * 2024/1/12
 */
public interface DataItemRepository {

    DataItem load(String code);

    void store(DataItem dataItem);

    boolean reStore(DataItem dataItem);

    boolean remove(String code);

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

    PageResult<DataItem> pageQuery(DataItemQuery query);
}
