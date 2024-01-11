package cn.doanything.basic.infrastructure.oss.channel;

import cn.doanything.basic.domain.oss.channel.OssChannel;
import cn.doanything.basic.domain.oss.channel.UploadResult;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @author wxj
 * 2024/1/11
 */
@Component(OssChannel.BEAN_PREFIX + "default")
public class DefaultOssChannel implements OssChannel {
    @Override
    public UploadResult upload(String key, InputStream input) {
        UploadResult result = new UploadResult();
        result.setSuccess(true);
        return result;
    }

    @Override
    public InputStream download(String key) {
        return null;
    }
}
