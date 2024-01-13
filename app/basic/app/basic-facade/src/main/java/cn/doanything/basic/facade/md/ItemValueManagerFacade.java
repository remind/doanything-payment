package cn.doanything.basic.facade.md;

import cn.doanything.basic.types.md.RowValue;
import cn.doanything.commons.response.ResponseResult;

import java.util.List;

/**
 * @author wxj
 * 2024/1/13
 */
public interface ItemValueManagerFacade {

    /**
     * 新增数据
     * @param rowValues 数据
     * @return
     */
    ResponseResult<String> addData(List<RowValue> rowValues);

    /**
     * 更新数据
     * @param rowValues
     * @return
     */
    ResponseResult<String> updateData(List<RowValue> rowValues);

    /**
     * 删除数据
     * @param itemCode
     * @param primaryValue
     * @return
     */
    ResponseResult<String> remove(String itemCode, String primaryValue);

    /**
     * 导入数据，如果存在就覆盖
     * @param rowValues
     * @return
     */
    ResponseResult<String> importData(List<RowValue> rowValues);
}
