package cn.doanything.basic.infrastructure.persistence.oss.repository;

import cn.doanything.basic.domain.oss.OssScene;
import cn.doanything.basic.domain.oss.repository.OssSceneConfigRepository;
import cn.doanything.basic.infrastructure.persistence.oss.convertor.OssSceneDalConvertor;
import cn.doanything.basic.infrastructure.persistence.oss.mapper.OssSceneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * 2024/1/11
 */
@Repository
public class OssSceneConfigRepositoryImpl implements OssSceneConfigRepository {

    @Autowired
    private OssSceneDalConvertor dalConvertor;

    @Autowired
    private OssSceneMapper dalMapper;
    @Override
    public OssScene load(String sceneCode) {
        return dalConvertor.toEntity(dalMapper.selectById(sceneCode));
    }
}
