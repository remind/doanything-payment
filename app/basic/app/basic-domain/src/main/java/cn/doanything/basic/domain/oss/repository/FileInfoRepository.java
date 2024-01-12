package cn.doanything.basic.domain.oss.repository;

import cn.doanything.basic.domain.oss.FileInfo;

import java.io.Serializable;

/**
 * @author wxj
 * 2024/1/11
 */
public interface FileInfoRepository {

    FileInfo store(FileInfo fileInfo);

    FileInfo load(Serializable fileId);
}
