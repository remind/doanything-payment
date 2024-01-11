package cn.doanything.basic.infrastructure.persistence.oss.repository;

import cn.doanything.basic.domain.oss.StorageObject;
import cn.doanything.basic.domain.oss.repository.StorageObjectRepository;
import cn.doanything.basic.infrastructure.persistence.oss.convertor.StorageObjectDalConvertor;
import cn.doanything.basic.infrastructure.persistence.oss.mapper.StorageObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * 2024/1/11
 */
@Repository
public class StorageObjectRepositoryImpl implements StorageObjectRepository {

    @Autowired
    private StorageObjectDalConvertor dalConvertor;

    @Autowired
    private StorageObjectMapper dalMapper;

    @Override
    public StorageObject load(String hash) {
        return dalConvertor.toEntity(dalMapper.selectById(hash));
    }

    @Override
    public void store(StorageObject storageObject) {
        dalMapper.insert(dalConvertor.toDo(storageObject));
    }
}
