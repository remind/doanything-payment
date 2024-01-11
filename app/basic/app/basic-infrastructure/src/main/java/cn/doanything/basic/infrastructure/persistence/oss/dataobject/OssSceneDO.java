package cn.doanything.basic.infrastructure.persistence.oss.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 存储场景
 * </p>
 *
 * @author wxj
 * @since 2024-01-11
 */
@TableName("to_oss_scene")
public class OssSceneDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    @TableId("code")
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 目录
     */
    private String directory;

    /**
     * 最大长度
     */
    private Double maxSize;

    /**
     * 最小长度
     */
    private Double minSize;

    /**
     * 支持的后缀
     */
    private String suffix;

    /**
     * 备注
     */
    private String memo;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 最后修改时间
     */
    private LocalDateTime gmtModified;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public Double getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Double maxSize) {
        this.maxSize = maxSize;
    }

    public Double getMinSize() {
        return minSize;
    }

    public void setMinSize(Double minSize) {
        this.minSize = minSize;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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
        return "OssSceneDO{" +
        "code = " + code +
        ", name = " + name +
        ", directory = " + directory +
        ", maxSize = " + maxSize +
        ", minSize = " + minSize +
        ", suffix = " + suffix +
        ", memo = " + memo +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        "}";
    }
}
