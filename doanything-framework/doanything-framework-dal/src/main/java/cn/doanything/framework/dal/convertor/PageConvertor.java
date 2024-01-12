package cn.doanything.framework.dal.convertor;

import cn.doanything.commons.convertor.ReadWriteConvertor;
import cn.doanything.commons.response.page.PageResult;
import cn.doanything.commons.response.page.Paging;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 分页查询convertor
 * @author wxj
 * 2024/1/12
 */
public interface PageConvertor<EntityType, DoType> extends ReadWriteConvertor<EntityType, DoType> {

    default PageResult<EntityType> toEntity(IPage<DoType> page) {
        PageResult<EntityType> pageResult = new PageResult<>();
        Paging paging = new Paging();
        paging.setCurrent(page.getCurrent());
        paging.setSize(page.getSize());
        pageResult.setPaging(paging);
        pageResult.setTotal(page.getTotal());
        pageResult.setRecords(toEntity(page.getRecords()));
        return pageResult;
    }

    default IPage<DoType> toPage(Paging paging) {
        IPage<DoType> page = new Page<>();
        page.setSize(paging.getSize());
        page.setCurrent(paging.getCurrent());
        return page;
    }
}
