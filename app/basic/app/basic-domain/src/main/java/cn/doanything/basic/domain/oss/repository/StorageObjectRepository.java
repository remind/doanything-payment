package cn.doanything.basic.domain.oss.repository;

import cn.doanything.basic.domain.oss.StorageObject;

/**
 * @author wxj
 * 2024/1/11
 */
public interface StorageObjectRepository {

    StorageObject load(String hash);

    void store(StorageObject storageObject);
}
