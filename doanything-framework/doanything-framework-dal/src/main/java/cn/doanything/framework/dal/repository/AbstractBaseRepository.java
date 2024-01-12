package cn.doanything.framework.dal.repository;

import cn.doanything.commons.convertor.ReadWriteConvertor;
import cn.doanything.framework.dal.mybatis.ext.ExtBaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * 公共Repository
 * @author wxj
 * 2024/1/12
 */
public abstract class AbstractBaseRepository<EntityType, DoType> {

    @Autowired
    protected ReadWriteConvertor<EntityType, DoType> dalConvertor;

    @Autowired
    protected ExtBaseMapper<DoType> dalMapper;

    public EntityType load(Serializable id) {
        Wrapper<DoType> idWrapper = getIdWrapper(id);
        if (idWrapper == null) {
            return dalConvertor.toEntity(dalMapper.selectById(id));
        }
        return dalConvertor.toEntity(dalMapper.selectOne(idWrapper));
    }

    public EntityType lock(Serializable id) {
        Wrapper<DoType> idWrapper = getIdWrapper(id);
        if (idWrapper == null) {
            return dalConvertor.toEntity(dalMapper.lockById(id));
        }
        return dalConvertor.toEntity(dalMapper.lockOne(idWrapper));
    }

    public EntityType store(EntityType entityType) {
        DoType doType = dalConvertor.toDo(entityType);
        if (dalMapper.insert(doType) == 1) {
            return dalConvertor.toEntity(doType); // 再转换一次，让实体对象的id赋值
        }
        return null;
    }

    public EntityType reStore(EntityType entityType) {
        DoType doType = dalConvertor.toDo(entityType);
        if (dalMapper.updateById(doType) == 1) {
            return dalConvertor.toEntity(doType); // 再转换一次，gmt_modified赋值
        }
        return null;
    }

    /**
     * 获取id条件
     * 某些业务主键不会被指定为数据库主键(即DO中字段上没有@TableId)，因此如果要根据业务主键查询，就是重写这个方法
     * @param id
     * @return
     */
    protected Wrapper<DoType> getIdWrapper(Serializable id) {
        return null;
    }
}
