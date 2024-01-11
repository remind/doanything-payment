package cn.doanything.basic.infrastructure.oss.channel;

import cn.doanything.basic.domain.oss.channel.OssChannel;
import cn.doanything.basic.domain.oss.channel.UploadResult;
import cn.doanything.commons.exceptions.BizException;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @author wxj
 * 2024/1/11
 */
@Slf4j
@Component(OssChannel.BEAN_PREFIX + "default")
public class DefaultOssChannel implements OssChannel {

    private static final String FILE_DIR = System.getProperty("user.home") + "/doanything/oss";

    @Override
    public UploadResult upload(String key, InputStream input) {
        UploadResult result = new UploadResult();
        result.setSuccess(true);

        File file = FileUtil.newFile(FILE_DIR + key);
        if (!file.getParentFile().exists()) {
            FileUtil.mkdir(file.getParentFile());
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            IoUtil.copy(input, outputStream);
            input.close();
            outputStream.close();
        } catch (Exception e) {
            log.error("文件保存异常", e);
            throw new BizException("文件保存异常");
        }
        return result;
    }

    @Override
    public InputStream download(String key) {
        try {
            File file = FileUtil.newFile(FILE_DIR + key);
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            log.error("文件获取异常", e);
            throw new BizException("文件获取异常");
        }
    }
}
