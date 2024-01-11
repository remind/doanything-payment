package cn.doanything.basic.infrastructure.persistence.oss.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 对象存储
 * </p>
 *
 * @author wxj
 * @since 2024-01-11
 */
@TableName("to_storage_object")
public class StorageObjectDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件hash值
     */
    @TableId("digest_hash")
    private String digestHash;

    /**
     * 在对象存储的key
     */
    private String storageKey;

    /**
     * 对象存储渠道
     */
    private String channel;

    /**
     * 文件大小
     */
    private Double size;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 最后修改时间
     */
    private LocalDateTime gmtModified;

    public String getDigestHash() {
        return digestHash;
    }

    public void setDigestHash(String digestHash) {
        this.digestHash = digestHash;
    }

    public String getStorageKey() {
        return storageKey;
    }

    public void setStorageKey(String storageKey) {
        this.storageKey = storageKey;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "StorageObjectDO{" +
        "digestHash = " + digestHash +
        ", storageKey = " + storageKey +
        ", channel = " + channel +
        ", size = " + size +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        "}";
    }
}
