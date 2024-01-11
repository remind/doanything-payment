package cn.doanything.basic.domain.oss.repository;

import cn.doanything.basic.domain.oss.OssScene;

/**
 * @author wxj
 * 2024/1/11
 */
public interface OssSceneConfigRepository {

    OssScene load(String sceneCode);
}
