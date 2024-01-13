package cn.doanything.basic.facade.md;

import cn.doanything.basic.facade.md.dto.DataItemAddRequest;
import cn.doanything.basic.facade.md.dto.DataItemResponse;
import cn.doanything.basic.types.md.DataItem;
import cn.doanything.basic.types.md.ItemField;
import cn.doanything.basic.types.md.query.DataItemQuery;
import cn.doanything.commons.response.ResponseResult;
import cn.doanything.commons.response.page.PageResult;

import java.util.List;

/**
 * @author wxj
 * 2024/1/12
 */
public interface DataItemManagerFacade {
    ResponseResult<PageResult<DataItem>> pageQuery(DataItemQuery query);

    ResponseResult<DataItem> getDataItem(String code);

    ResponseResult<String> addDataItem(DataItemAddRequest request);
    ResponseResult<String> updateDataItem(DataItemAddRequest request);
    ResponseResult<String> deleteDataItem(String code);

}
