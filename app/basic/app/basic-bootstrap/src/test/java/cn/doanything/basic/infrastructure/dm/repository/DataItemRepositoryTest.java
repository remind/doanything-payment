package cn.doanything.basic.infrastructure.dm.repository;

import cn.doanything.basic.types.md.DataItem;
import cn.doanything.basic.domain.md.repository.DataItemRepository;
import cn.doanything.basic.types.md.query.DataItemQuery;
import cn.doanything.commons.response.page.PageResult;
import cn.doanything.commons.response.page.Paging;
import cn.doanything.framework.BaseTestBootStarter;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wxj
 * 2024/1/12
 */
public class DataItemRepositoryTest extends BaseTestBootStarter {

    @Autowired
    private DataItemRepository dataItemRepository;

    @Test
    public void testPageQuery() {
        Paging paging = new Paging();
        DataItemQuery query = new DataItemQuery();
        query.setCode("area");
        query.setPaging(paging);
        PageResult<DataItem> result = dataItemRepository.pageQuery(query);
        Assert.assertNotNull(result);
    }
}
