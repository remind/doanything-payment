package cn.doanything.basic.infrastructure.persistence.oss.convertor;

import cn.doanything.basic.domain.oss.OssScene;
import cn.doanything.basic.infrastructure.persistence.oss.dataobject.OssSceneDO;
import cn.doanything.commons.convertor.GlobalTypeConvertor;
import cn.doanything.commons.convertor.ReadWriteConvertor;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2024/1/11
 */
@Mapper(componentModel = "spring", uses = {GlobalTypeConvertor.class})
public interface OssSceneDalConvertor extends ReadWriteConvertor<OssScene, OssSceneDO> {
}
