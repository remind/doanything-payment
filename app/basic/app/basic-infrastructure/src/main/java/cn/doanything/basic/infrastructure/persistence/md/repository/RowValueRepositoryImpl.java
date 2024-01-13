package cn.doanything.basic.infrastructure.persistence.md.repository;

import cn.doanything.basic.types.md.RowValue;
import cn.doanything.basic.domain.md.repository.RowValueRepository;
import cn.doanything.basic.infrastructure.persistence.md.convertor.FieldValueDalConvertor;
import cn.doanything.basic.infrastructure.persistence.md.convertor.ItemValueDalConvertor;
import cn.doanything.basic.infrastructure.persistence.md.dataobject.FieldValueDO;
import cn.doanything.basic.infrastructure.persistence.md.dataobject.ItemValueDO;
import cn.doanything.basic.infrastructure.persistence.md.mapper.FieldValueMapper;
import cn.doanything.basic.infrastructure.persistence.md.mapper.ItemValueMapper;
import cn.doanything.basic.types.md.FieldValue;
import cn.doanything.basic.types.md.query.RowValueQuery;
import cn.doanything.commons.response.page.PageResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wxj
 * 2024/1/13
 */
@Repository
public class RowValueRepositoryImpl implements RowValueRepository {

    @Autowired
    private ItemValueDalConvertor itemValueDalConvertor;

    @Autowired
    private FieldValueDalConvertor fieldValueDalConvertor;

    @Autowired
    private ItemValueMapper itemValueMapper;

    @Autowired
    private FieldValueMapper fieldValueMapper;

    @Override
    public PageResult<RowValue> pageQuery(RowValueQuery query, boolean fuzzy) {
        LambdaQueryWrapper<ItemValueDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ItemValueDO::getItemCode, query.getItemCode());
        if (fuzzy) {
            queryWrapper.like(ItemValueDO::getPrimaryValue, query.getPrimaryValue());
        } else {
            queryWrapper.eq(ItemValueDO::getPrimaryValue, query.getPrimaryValue());
        }
        IPage<ItemValueDO> page = itemValueMapper.selectPage(itemValueDalConvertor.toPage(query.getPaging()), queryWrapper);
        PageResult<RowValue> pageResult = itemValueDalConvertor.toEntity(page);
        fillFieldValue(query.getItemCode(), pageResult.getRecords());
        return pageResult;
    }

    @Override
    public RowValue load(String itemCode, String primaryValue) {
        RowValue rowValue = itemValueDalConvertor.toEntity(itemValueMapper.selectOne(buildIdQueryWrapper(itemCode, primaryValue)));
        if (rowValue != null) {
            fillFieldValue(itemCode, Collections.singletonList(rowValue));
        }
        return rowValue;
    }

    @Override
    public List<RowValue> loadAll(String itemCode) {
        List<RowValue> rowValues = itemValueDalConvertor.toEntity(
                itemValueMapper.selectList(
                        new LambdaQueryWrapper<ItemValueDO>().eq(ItemValueDO::getItemCode, itemCode)));
        fillFieldValue(itemCode, rowValues);
        return rowValues;
    }

    @Override
    public List<RowValue> loadByFiledValue(String itemCode, String filedCode, String fieldValue) {
        List<RowValue> rowValues = new ArrayList<>();
        Wrapper<FieldValueDO> queryWrapper = new LambdaQueryWrapper<FieldValueDO>()
                .eq(FieldValueDO::getItemCode, itemCode)
                .eq(FieldValueDO::getFieldCode, filedCode)
                .eq(FieldValueDO::getValue, fieldValue);
        List<FieldValueDO> fieldValueDOS = fieldValueMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(fieldValueDOS)) {
            Map<String, List<FieldValueDO>> fieldValueDOSMap = fieldValueDOS.stream().collect(Collectors.groupingBy(FieldValueDO::getPrimaryValue));
            List<ItemValueDO> itemValueDOS = itemValueMapper.selectList(new LambdaQueryWrapper<ItemValueDO>()
                    .eq(ItemValueDO::getItemCode, itemCode)
                    .in(ItemValueDO::getPrimaryValue, fieldValueDOSMap.keySet()));
            rowValues = itemValueDalConvertor.toEntity(itemValueDOS);
            rowValues.forEach(rowValue -> rowValue.setFieldValues(fieldValueDalConvertor.toEntity(fieldValueDOSMap.get(rowValue.getPrimaryValue()))));
        }
        return rowValues;
    }

    @Override
    public void store(RowValue rowValue) {
        itemValueMapper.insert(itemValueDalConvertor.toDo(rowValue));
        for (FieldValue fieldValue : rowValue.getFieldValues()) {
            fieldValueMapper.insert(fieldValueDalConvertor.toDo(rowValue, fieldValue));
        }
    }

    @Override
    public boolean reStore(RowValue rowValue) {
        if (itemValueMapper.exists(buildIdQueryWrapper(rowValue.getItemCode(), rowValue.getPrimaryValue()))) {
            for (FieldValue fieldValue : rowValue.getFieldValues()) {
                fieldValueMapper.update(fieldValueDalConvertor.toDo(rowValue, fieldValue)
                        , buildIdQueryWrapper(rowValue.getItemCode(), rowValue.getPrimaryValue(), fieldValue.getFieldCode()));
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(String itemCode, String primaryValue) {
        if (itemValueMapper.delete(buildIdQueryWrapper(itemCode, primaryValue)) == 1) {
            fieldValueMapper.delete(buildFieldQueryWrapper(itemCode, primaryValue));
            return true;
        }
        return false;
    }

    private void fillFieldValue(String itemCode, List<RowValue> rowValues) {
        if (!CollectionUtils.isEmpty(rowValues)) {
            List<String> primaryValues = rowValues.stream().map(RowValue::getPrimaryValue).toList();
            Wrapper<FieldValueDO> queryWrapper = new LambdaQueryWrapper<FieldValueDO>()
                    .eq(FieldValueDO::getItemCode, itemCode)
                    .in(FieldValueDO::getPrimaryValue, primaryValues);
            List<FieldValueDO> fieldValueDOS = fieldValueMapper.selectList(queryWrapper);
            if (!CollectionUtils.isEmpty(fieldValueDOS)) {
                Map<String, List<FieldValueDO>> fieldValueDOSMap = fieldValueDOS.stream().collect(Collectors.groupingBy(FieldValueDO::getPrimaryValue));
                rowValues.forEach(rowValue -> rowValue.setFieldValues(fieldValueDalConvertor.toEntity(fieldValueDOSMap.get(rowValue.getPrimaryValue()))));
            }
        }
    }

    private Wrapper<ItemValueDO> buildIdQueryWrapper(String itemCode, String primaryValue) {
        return new LambdaQueryWrapper<ItemValueDO>().eq(ItemValueDO::getItemCode, itemCode)
                .eq(ItemValueDO::getPrimaryValue, primaryValue);
    }

    private Wrapper<FieldValueDO> buildIdQueryWrapper(String itemCode, String primaryValue, String fieldCode) {
        return new LambdaQueryWrapper<FieldValueDO>().eq(FieldValueDO::getItemCode, itemCode)
                .eq(FieldValueDO::getPrimaryValue, primaryValue)
                .eq(FieldValueDO::getFieldCode, fieldCode);
    }

    private Wrapper<FieldValueDO> buildFieldQueryWrapper(String itemCode, String primaryValue) {
        return new LambdaQueryWrapper<FieldValueDO>().eq(FieldValueDO::getItemCode, itemCode)
                .eq(FieldValueDO::getPrimaryValue, primaryValue);
    }
}
