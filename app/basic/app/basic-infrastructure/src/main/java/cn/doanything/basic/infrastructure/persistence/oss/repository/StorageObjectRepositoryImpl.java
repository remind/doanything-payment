package cn.doanything.basic.infrastructure.persistence.oss.repository;

import cn.doanything.basic.domain.oss.StorageObject;
import cn.doanything.basic.domain.oss.repository.StorageObjectRepository;
import cn.doanything.basic.infrastructure.persistence.oss.dataobject.StorageObjectDO;
import cn.doanything.framework.dal.repository.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * 2024/1/11
 */
@Repository
public class StorageObjectRepositoryImpl extends AbstractBaseRepository<StorageObject, StorageObjectDO, String> implements StorageObjectRepository {


}
