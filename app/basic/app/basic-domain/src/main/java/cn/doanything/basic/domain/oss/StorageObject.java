package cn.doanything.basic.domain.oss;

import cn.doanything.commons.lang.Entity;
import lombok.Data;

/**
 * 存储对象
 * @author wxj
 * 2024/1/11
 */
@Data
public class StorageObject extends Entity {

    /**
     * 文件hash
     */
    private String hash;

    /**
     * 标识，在渠道唯一
     */
    private String key;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 大小
     */
    private Long size;
}
