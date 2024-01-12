package cn.doanything.basic.infrastructure.persistence.oss.repository;

import cn.doanything.basic.domain.oss.OssScene;
import cn.doanything.basic.domain.oss.repository.OssSceneConfigRepository;
import cn.doanything.basic.infrastructure.persistence.oss.dataobject.OssSceneDO;
import cn.doanything.framework.dal.repository.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * 2024/1/11
 */
@Repository
public class OssSceneConfigRepositoryImpl extends AbstractBaseRepository<OssScene, OssSceneDO> implements OssSceneConfigRepository {


}
