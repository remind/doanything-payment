package cn.doanything.basic.domain.oss.repository;

import cn.doanything.basic.domain.oss.StorageObject;

import java.io.Serializable;

/**
 * @author wxj
 * 2024/1/11
 */
public interface StorageObjectRepository {

    StorageObject load(Serializable hash);

    StorageObject store(StorageObject storageObject);

    StorageObject lock(Serializable hash);

    StorageObject reStore(StorageObject storageObject);
}
