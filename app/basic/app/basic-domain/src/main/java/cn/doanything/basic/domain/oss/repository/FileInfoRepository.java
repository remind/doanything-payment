package cn.doanything.basic.domain.oss.repository;

import cn.doanything.basic.domain.oss.FileInfo;

/**
 * @author wxj
 * 2024/1/11
 */
public interface FileInfoRepository {

    void store(FileInfo fileInfo);

    FileInfo load(String fileId);
}
