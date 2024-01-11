package cn.doanything.basic.infrastructure.persistence.oss.convertor;

import cn.doanything.basic.domain.oss.FileInfo;
import cn.doanything.basic.infrastructure.persistence.oss.dataobject.FileInfoDO;
import cn.doanything.commons.convertor.ReadWriteConvertor;
import org.mapstruct.Mapper;

/**
 * @author wxj
 * 2024/1/11
 */
@Mapper(componentModel = "spring")
public interface FileInfoDalConvertor extends ReadWriteConvertor<FileInfo, FileInfoDO> {
}
