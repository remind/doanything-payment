package cn.doanything.basic.facade.dm;

import cn.doanything.basic.facade.md.ItemValueQueryFacade;
import cn.doanything.basic.types.md.RowValue;
import cn.doanything.basic.types.md.query.RowValueQuery;
import cn.doanything.commons.response.ResponseResult;
import cn.doanything.commons.response.page.PageResult;
import cn.doanything.framework.BaseTestBootStarter;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wxj
 * 2024/1/13
 */
public class ItemValueQueryFacadeTest extends BaseTestBootStarter {

    @Autowired
    private ItemValueQueryFacade itemValueQueryFacade;

    @Test
    public void testLoadAll() {
        ResponseResult<List<RowValue>> responseResult = itemValueQueryFacade.loadAll("provinces");
        Assert.assertTrue(responseResult.isSuccess());
    }

    @Test
    public void testLoadOne() {
        ResponseResult<RowValue> responseResult = itemValueQueryFacade.load("provinces", "11");
        Assert.assertTrue(responseResult.isSuccess());
    }
    @Test
    public void testPageQuery() {
        RowValueQuery query = new RowValueQuery();
        query.setItemCode("provinces");
        query.setPrimaryValue("1");
        ResponseResult<PageResult<RowValue>> responseResult = itemValueQueryFacade.pageQuery(query, true);
        Assert.assertTrue(responseResult.isSuccess());
    }


}
