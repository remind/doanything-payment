package cn.doanything.basic.facade.md;

import cn.doanything.basic.domain.md.repository.RowValueRepository;
import cn.doanything.basic.types.md.RowValue;
import cn.doanything.commons.response.ResponseResult;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * @author wxj
 * 2024/1/13
 */
@DubboService
public class ItemValueManagerFacadeImpl implements ItemValueManagerFacade {

    @Autowired
    private RowValueRepository rowValueRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public ResponseResult<String> addData(List<RowValue> rowValues) {
        transactionTemplate.executeWithoutResult(status -> {
            for (RowValue rowValue : rowValues) {
                rowValueRepository.store(rowValue);
            }
        });
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<String> updateData(List<RowValue> rowValues) {
        transactionTemplate.executeWithoutResult(status -> {
            for (RowValue rowValue : rowValues) {
                rowValueRepository.reStore(rowValue);
            }
        });
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<String> remove(String itemCode, String primaryValue) {
        transactionTemplate.executeWithoutResult(status -> {
            rowValueRepository.remove(itemCode, primaryValue);
        });
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<String> importData(List<RowValue> rowValues) {
        transactionTemplate.executeWithoutResult(status -> {
            for (RowValue rowValue : rowValues) {
                try {
                    rowValueRepository.store(rowValue);
                } catch (DuplicateKeyException e) {
                    rowValueRepository.reStore(rowValue);
                }
            }
        });
        return ResponseResult.success();
    }
}
