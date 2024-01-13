package cn.doanything.basic.facade.md;

import cn.doanything.basic.application.md.builder.DataItemBuilder;
import cn.doanything.basic.domain.md.repository.DataItemRepository;
import cn.doanything.basic.facade.md.dto.DataItemAddRequest;
import cn.doanything.basic.types.md.DataItem;
import cn.doanything.basic.types.md.query.DataItemQuery;
import cn.doanything.commons.lang.utils.AssertUtil;
import cn.doanything.commons.response.ResponseResult;
import cn.doanything.commons.response.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wxj
 * 2024/1/13
 */
public class DataItemManagerFacadeImpl implements DataItemManagerFacade {

    @Autowired
    private DataItemRepository dataItemRepository;

    @Autowired
    private DataItemBuilder dataItemBuilder;

    @Override
    public ResponseResult<PageResult<DataItem>> pageQuery(DataItemQuery query) {
        return ResponseResult.success(dataItemRepository.pageQuery(query));
    }

    @Override
    public ResponseResult<DataItem> getDataItem(String code) {
        return ResponseResult.success(dataItemRepository.load(code));
    }

    @Override
    public ResponseResult<String> addDataItem(DataItemAddRequest request) {
        AssertUtil.isNull(dataItemRepository.load(request.getCode()), "编码已经存在");
        DataItem dataItem = dataItemBuilder.build(request);
        dataItemRepository.store(dataItem);
        return ResponseResult.success(dataItem.getCode());
    }

    @Override
    public ResponseResult<String> updateDataItem(DataItemAddRequest request) {
        DataItem dataItem = dataItemBuilder.build(request);
        if (dataItemRepository.reStore(dataItem)) {
            return ResponseResult.success();
        } else {
            return ResponseResult.fail();
        }
    }

    @Override
    public ResponseResult<String> deleteDataItem(String code) {
        if (dataItemRepository.remove(code)) {
            return ResponseResult.success();
        } else {
            return ResponseResult.fail();
        }
    }
}
