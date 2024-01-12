package cn.doanything.basic.infrastructure.persistence.oss.repository;

import cn.doanything.basic.domain.oss.FileInfo;
import cn.doanything.basic.domain.oss.repository.FileInfoRepository;
import cn.doanything.basic.infrastructure.persistence.oss.dataobject.FileInfoDO;
import cn.doanything.framework.dal.repository.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * 2024/1/11
 */
@Repository
public class FileInfoRepositoryImpl extends AbstractBaseRepository<FileInfo, FileInfoDO> implements FileInfoRepository {


}
