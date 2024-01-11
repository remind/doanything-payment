package cn.doanything.basic.application.oss;

import cn.doanything.basic.domain.BasicConstants;
import cn.doanything.basic.domain.IdType;
import cn.doanything.basic.domain.oss.FileInfo;
import cn.doanything.basic.domain.oss.OssScene;
import cn.doanything.basic.domain.oss.StorageObject;
import cn.doanything.basic.domain.oss.channel.OssChannel;
import cn.doanything.basic.domain.oss.channel.UploadResult;
import cn.doanything.basic.domain.oss.repository.FileInfoRepository;
import cn.doanything.basic.domain.oss.repository.OssSceneConfigRepository;
import cn.doanything.basic.domain.oss.repository.StorageObjectRepository;
import cn.doanything.basic.facade.oss.dto.UploadFile;
import cn.doanything.commons.enums.SystemCodeEnums;
import cn.doanything.commons.exceptions.BizException;
import cn.doanything.commons.lang.utils.AssertUtil;
import cn.doanything.framework.api.sequence.SequenceService;
import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 文件上传服务
 *
 * @author wxj
 * 2024/1/11
 */
@Service
@Slf4j
public class FileUploadService {

    @Autowired
    private StorageObjectRepository storageObjectRepository;

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private OssSceneConfigRepository ossSceneConfigRepository;

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private Map<String, OssChannel> ossChannelMap;

    public FileInfo upload(UploadFile uploadFile) {
        OssScene ossScene = ossSceneConfigRepository.load(uploadFile.getScene());
        uploadFile.setInput(convert(uploadFile.getInput()));
        validate(ossScene, uploadFile);
        FileInfo fileInfo = buildFileInfo(uploadFile);
        if (storageObjectRepository.load(fileInfo.getDigestHash()) == null) {
            String key = buildStorageKey(fileInfo, ossScene);
            String channelCode = BasicConstants.OSS_DEFAULT_CHANNEL;
            UploadResult result = ossChannelMap.get(OssChannel.BEAN_PREFIX + channelCode)
                    .upload(key, uploadFile.getInput());
            AssertUtil.isTrue(result.isSuccess(), "文件上传失败");

            StorageObject storageObject = new StorageObject();
            storageObject.setDigestHash(fileInfo.getDigestHash());
            storageObject.setStorageKey(key);
            storageObject.setChannel(channelCode);
            storageObject.setSize(uploadFile.getSize());
            storageObjectRepository.store(storageObject);
        }
        fileInfoRepository.store(fileInfo);
        return fileInfo;
    }

    private String buildStorageKey(FileInfo fileInfo, OssScene ossScene) {
        return ossScene.getDirectory() + "/" + fileInfo.getDigestHash() + "/" + fileInfo.getOriginName();
    }

    private void validate(OssScene ossScene, UploadFile uploadFile) {
        AssertUtil.isTrue(uploadFile.getSize() <= ossScene.getMaxSize() * 1024 * 1024, "文件超过最大限制");
        AssertUtil.isTrue(uploadFile.getSize() >= ossScene.getMinSize() * 1024 * 1024, "文件小于最小限制");

        String suffix = FileUtil.getSuffix(uploadFile.getFileName());
        AssertUtil.isTrue(ossScene.supportSuffix(suffix), "文件格式不支持");
    }

    private FileInfo buildFileInfo(UploadFile uploadFile) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileId(sequenceService.getId(uploadFile.getMemberId(), SystemCodeEnums.BASIC, IdType.OSS_FILE_INFO));
        fileInfo.setDigestHash(getFileHash(uploadFile.getInput()));
        fileInfo.setOriginName(uploadFile.getFileName());
        fileInfo.setMemberId(uploadFile.getMemberId());
        fileInfo.setScene(uploadFile.getScene());
        fileInfo.setSuffix(FileUtil.getSuffix(uploadFile.getFileName()));
        return fileInfo;
    }

    private String getFileHash(InputStream input) {
        try {
            input.mark(0);
            String hash = SecureUtil.md5(input);
            input.reset();
            return hash;
        } catch (IOException e) {
            log.error("获取文件hash异常", e);
            throw new BizException("文件上传失败");
        }
    }

    /**
     * 转换为可mark的流，以方便重复处理
     * @param inputStream
     * @return
     */
    private static InputStream convert(InputStream inputStream) {
        if (!inputStream.markSupported()) {
            // 如果不支持mark，则转换为BufferedInputStream
            return new BufferedInputStream(inputStream);
        }
        return inputStream;
    }
}
