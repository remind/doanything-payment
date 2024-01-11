package cn.doanything.basic.application.oss;

import cn.doanything.basic.domain.oss.FileInfo;
import cn.doanything.basic.domain.oss.StorageObject;
import cn.doanything.basic.domain.oss.channel.OssChannel;
import cn.doanything.basic.domain.oss.repository.FileInfoRepository;
import cn.doanything.basic.domain.oss.repository.StorageObjectRepository;
import cn.doanything.commons.lang.utils.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;

/**
 * 文件下载服务
 *
 * @author wxj
 * 2024/1/11
 */
@Service
@Slf4j
public class FileDownloadService {

    @Autowired
    private StorageObjectRepository storageObjectRepository;

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private Map<String, OssChannel> ossChannelMap;

    public InputStream download(String fileId) {
        FileInfo fileInfo = fileInfoRepository.load(fileId);
        AssertUtil.isNotNull(fileInfo, "文件不存在");
        StorageObject storageObject = storageObjectRepository.load(fileInfo.getHash());
        return ossChannelMap.get(OssChannel.BEAN_PREFIX + storageObject.getChannel()).download(storageObject.getKey());
    }
}
