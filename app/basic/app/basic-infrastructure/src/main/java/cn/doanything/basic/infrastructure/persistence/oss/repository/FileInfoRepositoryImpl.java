package cn.doanything.basic.infrastructure.persistence.oss.repository;

import cn.doanything.basic.domain.oss.FileInfo;
import cn.doanything.basic.domain.oss.repository.FileInfoRepository;
import cn.doanything.basic.infrastructure.persistence.oss.convertor.FileInfoDalConvertor;
import cn.doanything.basic.infrastructure.persistence.oss.mapper.FileInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wxj
 * 2024/1/11
 */
@Repository
public class FileInfoRepositoryImpl implements FileInfoRepository {

    @Autowired
    private FileInfoDalConvertor dalConvertor;

    @Autowired
    private FileInfoMapper dalMapper;
    @Override
    public void store(FileInfo fileInfo) {
        dalMapper.insert(dalConvertor.toDo(fileInfo));
    }

    @Override
    public FileInfo load(String fileId) {
        return dalConvertor.toEntity(dalMapper.selectById(fileId));
    }
}
