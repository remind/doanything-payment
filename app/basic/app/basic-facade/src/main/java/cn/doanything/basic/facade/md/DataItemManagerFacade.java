package cn.doanything.basic.facade.md;

import cn.doanything.basic.facade.md.dto.DataItemAddRequest;
import cn.doanything.commons.response.ResponseResult;

/**
 * @author wxj
 * 2024/1/12
 */
public interface DataItemManagerFacade {

    ResponseResult<String> addDataItem(DataItemAddRequest request);

}
