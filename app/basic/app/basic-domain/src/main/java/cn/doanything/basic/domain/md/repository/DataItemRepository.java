package cn.doanything.basic.domain.md.repository;

import cn.doanything.basic.domain.md.DataItem;
import cn.doanything.basic.types.md.ItemField;

/**
 * @author wxj
 * 2024/1/12
 */
public interface DataItemRepository {

    DataItem load(String code);

    DataItem store(DataItem dataItem);

    DataItem reStore(DataItem dataItem);

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
}
