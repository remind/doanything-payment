package cn.doanything.basic.facade.md;

import cn.doanything.basic.domain.md.repository.RowValueRepository;
import cn.doanything.basic.types.md.RowValue;
import cn.doanything.basic.types.md.query.RowValueQuery;
import cn.doanything.commons.response.ResponseResult;
import cn.doanything.commons.response.page.PageResult;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wxj
 * 2024/1/13
 */
@DubboService
public class ItemValueQueryFacadeImpl implements ItemValueQueryFacade {

    @Autowired
    private RowValueRepository rowValueRepository;

    @Override
    public ResponseResult<List<RowValue>> loadAll(String itemCode) {
        return ResponseResult.success(rowValueRepository.loadAll(itemCode));
    }

    @Override
    public ResponseResult<RowValue> load(String itemCode, String primaryValue) {
        return ResponseResult.success(rowValueRepository.load(itemCode, primaryValue));
    }

    @Override
    public ResponseResult<PageResult<RowValue>> pageQuery(RowValueQuery query, boolean fuzzy) {
        return ResponseResult.success(rowValueRepository.pageQuery(query, fuzzy));
    }
}
