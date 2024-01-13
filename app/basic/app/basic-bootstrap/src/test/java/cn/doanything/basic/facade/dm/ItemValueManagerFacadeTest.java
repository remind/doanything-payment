package cn.doanything.basic.facade.dm;

import cn.doanything.basic.facade.md.ItemValueManagerFacade;
import cn.doanything.basic.types.md.FieldValue;
import cn.doanything.basic.types.md.RowValue;
import cn.doanything.commons.response.ResponseResult;
import cn.doanything.framework.BaseTestBootStarter;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxj
 * 2024/1/13
 */
public class ItemValueManagerFacadeTest extends BaseTestBootStarter {

    @Autowired
    private ItemValueManagerFacade itemValueManagerFacade;

    @Test
    public void testAddData() {
        List<RowValue> rowValues = new ArrayList<>();
        rowValues.add(buildRowValue("11", "北京"));
        rowValues.add(buildRowValue("12", "天津"));
        ResponseResult<String> result = itemValueManagerFacade.addData(rowValues);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void testUpdateData() {
        List<RowValue> rowValues = new ArrayList<>();
        rowValues.add(buildRowValue("11", "北京1"));
        rowValues.add(buildRowValue("12", "天津1"));
        ResponseResult<String> result = itemValueManagerFacade.updateData(rowValues);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void testRemoveData() {
        ResponseResult<String> result = itemValueManagerFacade.remove("provinces", "11");
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void testImportData() {
        List<RowValue> rowValues = new ArrayList<>();
        rowValues.add(buildRowValue("11", "北京"));
        rowValues.add(buildRowValue("12", "天津"));
        ResponseResult<String> result = itemValueManagerFacade.importData(rowValues);
        Assert.assertTrue(result.isSuccess());
    }

    private RowValue buildRowValue(String code, String name) {
        RowValue rowValue = new RowValue();
        rowValue.setItemCode("provinces");
        rowValue.setPrimaryValue(code);
        List<FieldValue> fieldValues = new ArrayList<>();
        fieldValues.add(new FieldValue("code", code));
        fieldValues.add(new FieldValue("name", name));
        rowValue.setFieldValues(fieldValues);
        return rowValue;
    }
}
