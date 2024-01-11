package cn.doanything.basic.facade.oss.dto;

import lombok.Data;

import java.io.InputStream;

/**
 * 上传文件
 * @author wxj
 * 2024/1/11
 */
@Data
public class UploadFile {

    /**
     * 会员ID
     */
    String memberId;

    /**
     * 场景
     */
    String scene;

    /**
     * 文件流
     */
    InputStream input;

    /**
     * 文件名称
     */
    String fileName;

    /**
     * 文件长度
     */
    Long size;
}
