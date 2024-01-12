package cn.doanything.basic.infrastructure.persistence.md.repository;

import cn.doanything.basic.domain.md.DataItem;
import cn.doanything.basic.domain.md.repository.DataItemRepository;
import cn.doanything.basic.infrastructure.persistence.md.convertor.DataItemDalConvertor;
import cn.doanything.basic.infrastructure.persistence.md.convertor.ItemFieldDalConvertor;
import cn.doanything.basic.infrastructure.persistence.md.dataobject.DataItemDO;
import cn.doanything.basic.infrastructure.persistence.md.dataobject.ItemFieldDO;
import cn.doanything.basic.infrastructure.persistence.md.mapper.DataItemMapper;
import cn.doanything.basic.infrastructure.persistence.md.mapper.ItemFieldMapper;
import cn.doanything.basic.types.md.ItemField;
import cn.doanything.basic.types.md.query.DataItemQuery;
import cn.doanything.commons.response.page.PageResult;
import cn.doanything.commons.response.page.Paging;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wxj
 * 2024/1/12
 */
@Repository
public class DataItemRepositoryImpl implements DataItemRepository {

    @Autowired
    private DataItemDalConvertor dalConvertor;

    @Autowired
    private ItemFieldDalConvertor fieldDalConvertor;

    @Autowired
    private DataItemMapper dalMapper;

    @Autowired
    private ItemFieldMapper fieldMapper;

    @Override
    public DataItem load(String code) {
        DataItem dataItem = dalConvertor.toEntity(dalMapper.selectById(code));
        if (dataItem != null) {
            List<ItemFieldDO> itemFieldDOS = fieldMapper.selectList(new LambdaQueryWrapper<ItemFieldDO>().eq(ItemFieldDO::getItemCode, dataItem.getCode()));
            dataItem.setItemFields(fieldDalConvertor.toEntity(itemFieldDOS));
        }
        return dataItem;
    }

    @Override
    public void store(DataItem dataItem) {
        dalMapper.insert(dalConvertor.toDo(dataItem));
        if (dataItem.getItemFields() != null) {
            for (ItemField itemField : dataItem.getItemFields()) {
                fieldMapper.insert(fieldDalConvertor.toDo(itemField));
            }
        }
    }

    @Override
    public void reStore(DataItem dataItem) {
        dalMapper.updateById(dalConvertor.toDo(dataItem));
    }

    @Override
    public void addItemField(ItemField itemField) {
        fieldMapper.insert(fieldDalConvertor.toDo(itemField));
    }

    @Override
    public void updateItemField(ItemField itemField) {
        fieldMapper.update(fieldDalConvertor.toDo(itemField), getFieldIdWrapper(itemField.getItemCode(), itemField.getCode()));
    }

    @Override
    public void deleteItemField(String itemCode, String fieldCode) {
        fieldMapper.delete(getFieldIdWrapper(itemCode, fieldCode));
    }

    @Override
    public PageResult<DataItem> pageQuery(DataItemQuery query, Paging paging) {
        LambdaQueryWrapper<DataItemDO> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(query.getCode())) {
            wrapper.like(DataItemDO::getCode, query.getCode());
        }
        if (StrUtil.isNotBlank(query.getName())) {
            wrapper.like(DataItemDO::getName, query.getName());
        }
        IPage<DataItemDO> pageDo = dalMapper.selectPage(dalConvertor.toPage(paging), wrapper);
        return dalConvertor.toEntity(pageDo);
    }

    private Wrapper<ItemFieldDO> getFieldIdWrapper(String itemCode, String fieldCode) {
        return new LambdaQueryWrapper<ItemFieldDO>().eq(ItemFieldDO::getItemCode, itemCode)
                .eq(ItemFieldDO::getCode, fieldCode);
    }
}
