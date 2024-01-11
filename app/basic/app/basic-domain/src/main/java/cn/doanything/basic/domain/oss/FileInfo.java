package cn.doanything.basic.domain.oss;

import cn.doanything.commons.lang.Entity;
import lombok.Data;

/**
 * 文件信息
 * @author wxj
 * 2024/1/11
 */
@Data
public class FileInfo extends Entity {

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 场景编码
     */
    private String scene;

    /**
     * 文件hash值
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

}
