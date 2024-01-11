package cn.doanything.basic.infrastructure.persistence.oss.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 文件信息
 * </p>
 *
 * @author wxj
 * @since 2024-01-11
 */
@TableName("to_file_info")
public class FileInfoDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件ID
     */
    @TableId("file_id")
    private String fileId;

    /**
     * 场景
     */
    private String scene;

    /**
     * 文件hash
     */
    private String digestHash;

    /**
     * 会员ID
     */
    private String memberId;

    /**
     * 原始名称
     */
    private String originName;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 最后修改时间
     */
    private LocalDateTime gmtModified;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getDigestHash() {
        return digestHash;
    }

    public void setDigestHash(String digestHash) {
        this.digestHash = digestHash;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
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
        return "FileInfoDO{" +
        "fileId = " + fileId +
        ", scene = " + scene +
        ", digestHash = " + digestHash +
        ", memberId = " + memberId +
        ", originName = " + originName +
        ", suffix = " + suffix +
        ", gmtCreate = " + gmtCreate +
        ", gmtModified = " + gmtModified +
        "}";
    }
}
