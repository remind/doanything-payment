package cn.doanything.basic.infrastructure.persistence.md.repository;

import cn.doanything.basic.domain.md.DataItem;
import cn.doanything.basic.domain.md.repository.DataItemRepository;
import cn.doanything.basic.infrastructure.persistence.md.convertor.DataItemDalConvertor;
import cn.doanything.basic.infrastructure.persistence.md.dataobject.ItemFieldDO;
import cn.doanything.basic.infrastructure.persistence.md.mapper.DataItemMapper;
import cn.doanything.basic.types.md.ItemField;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * 2024/1/12
 */
@Repository
public class DataItemRepositoryImpl implements DataItemRepository {

    @Autowired
    private DataItemDalConvertor dalConvertor;

    @Autowired
    private DataItemMapper dalMapper;

    @Override
    public DataItem load(String code) {
        DataItem dataItem = dalConvertor.toEntity(dalMapper.selectById(code));
        return null;
    }

    @Override
    public DataItem store(DataItem dataItem) {
        return null;
    }

    @Override
    public DataItem reStore(DataItem dataItem) {
        return null;
    }

    @Override
    public void addItemField(ItemField itemField) {

    }

    @Override
    public void updateItemField(ItemField itemField) {

    }

    @Override
    public void deleteItemField(String itemCode, String fieldCode) {

    }

    private Wrapper<ItemFieldDO> getFieldIdWrapper(String itemCode, String fieldCode) {
        return new LambdaQueryWrapper<ItemFieldDO>().eq(ItemFieldDO::getItemCode, itemCode)
                .eq(ItemFieldDO::getCode, fieldCode);
    }
}
