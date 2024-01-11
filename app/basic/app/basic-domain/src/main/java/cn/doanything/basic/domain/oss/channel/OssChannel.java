package cn.doanything.basic.domain.oss.channel;

import java.io.InputStream;

/**
 * 对象存储渠道
 * @author wxj
 * 2024/1/11
 */
public interface OssChannel {

    String BEAN_PREFIX = "OssChannel_prefix_";

    /**
     * 上传
     * @param key
     * @param input
     * @return
     */
    UploadResult upload(String key, InputStream input);

    /**
     * 下载
     * @param key
     * @return
     */
    InputStream download(String key);

}
