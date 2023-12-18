package cn.doanything.framework.dal.mybatis.ext;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;

/**
 * @author wxj
 * 2023/12/18
 */
public interface ExtBaseMapper<T> extends BaseMapper<T> {

    T lockById(Serializable id);
}
