package cn.doanything.basic.application.oss;

import cn.doanything.basic.domain.BasicConstants;
import cn.doanything.basic.domain.IdType;
import cn.doanything.basic.domain.oss.channel.OssChannel;
import cn.doanything.basic.domain.oss.FileInfo;
import cn.doanything.basic.domain.oss.OssSceneConfig;
import cn.doanything.basic.domain.oss.StorageObject;
import cn.doanything.basic.domain.oss.channel.UploadResult;
import cn.doanything.basic.domain.oss.repository.FileInfoRepository;
import cn.doanything.basic.domain.oss.repository.OssSceneConfigRepository;
import cn.doanything.basic.domain.oss.repository.StorageObjectRepository;
import cn.doanything.commons.enums.SystemCodeEnums;
import cn.doanything.commons.exceptions.BizException;
import cn.doanything.commons.lang.utils.AssertUtil;
import cn.doanything.framework.api.sequence.SequenceService;
import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public void upload(String memberId, String sceneCode, MultipartFile file) {
        OssSceneConfig ossSceneConfig = ossSceneConfigRepository.load(sceneCode);
        validate(ossSceneConfig, file);
        FileInfo fileInfo = buildFileInfo(memberId, sceneCode, file);
        if (storageObjectRepository.load(fileInfo.getHash()) == null) {
            String key = buildStorageKey(fileInfo, ossSceneConfig);
            try {
                String channelCode = BasicConstants.OSS_DEFAULT_CHANNEL;
                UploadResult result = ossChannelMap.get(OssChannel.BEAN_PREFIX + channelCode)
                        .upload(key, file.getInputStream());
                AssertUtil.isTrue(result.isSuccess(), "文件上传失败");

                StorageObject storageObject = new StorageObject();
                storageObject.setHash(fileInfo.getHash());
                storageObject.setKey(key);
                storageObject.setChannel(channelCode);
                storageObject.setSize(file.getSize());
                storageObjectRepository.store(storageObject);
            } catch (IOException e) {
                log.error("文件上传失败", e);
                throw new BizException("文件上传失败");
            }
        }
        fileInfoRepository.store(fileInfo);
    }

    private String buildStorageKey(FileInfo fileInfo, OssSceneConfig ossSceneConfig) {
        return SecureUtil.md5(fileInfo.getOriginName() + fileInfo.getMemberId() + fileInfo.getSceneCode());
    }

    private void validate(OssSceneConfig ossSceneConfig, MultipartFile file) {
        long size = file.getSize();
        AssertUtil.isTrue(size <= ossSceneConfig.getMaxSize(), "文件超过最大限制");
        AssertUtil.isTrue(size >= ossSceneConfig.getMinSize(), "文件小于最小限制");

        String suffix = FileUtil.getSuffix(file.getOriginalFilename());
        AssertUtil.isTrue(ossSceneConfig.supportSuffix(suffix), "文件格式不支持");
    }

    private FileInfo buildFileInfo(String memberId, String sceneCode, MultipartFile file) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileId(sequenceService.getId(memberId, SystemCodeEnums.BASIC, IdType.OSS_FILE_INFO));
        fileInfo.setHash(getFileHash(file));
        fileInfo.setOriginName(file.getOriginalFilename());
        fileInfo.setMemberId(memberId);
        fileInfo.setSceneCode(sceneCode);
        fileInfo.setSuffix(FileUtil.getSuffix(file.getOriginalFilename()));
        return fileInfo;
    }

    private String getFileHash(MultipartFile file) {
        InputStream is = null;
        try {
            is = new BufferedInputStream(file.getInputStream());
            is.mark(is.available());
            String hash = SecureUtil.md5(is);
            is.reset();
            return hash;
        } catch (IOException e) {
            log.error("获取文件hash异常", e);
            throw new BizException("文件上传失败");
        }
    }
}
