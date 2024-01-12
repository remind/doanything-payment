package cn.doanything.basic.domain.oss.repository;

import cn.doanything.basic.domain.oss.OssScene;

import java.io.Serializable;

/**
 * @author wxj
 * 2024/1/11
 */
public interface OssSceneConfigRepository {

    OssScene load(Serializable sceneCode);
}
