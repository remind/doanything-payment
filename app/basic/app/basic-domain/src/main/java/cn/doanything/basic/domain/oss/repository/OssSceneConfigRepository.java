package cn.doanything.basic.domain.oss.repository;

import cn.doanything.basic.domain.oss.OssSceneConfig;

/**
 * @author wxj
 * 2024/1/11
 */
public interface OssSceneConfigRepository {

    OssSceneConfig load(String sceneCode);
}
