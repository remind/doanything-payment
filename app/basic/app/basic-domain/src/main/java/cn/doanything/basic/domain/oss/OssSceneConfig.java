package cn.doanything.basic.domain.oss;

import lombok.Data;

import java.util.List;

/**
 * 存储场景配置
 * @author wxj
 * 2024/1/11
 */
@Data
public class OssSceneConfig {

    /**
     * 编码
     */
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
     * 最大
     */
    private Long maxSize;

    /**
     * 最小
     */
    private Long minSize;

    /**
     * 文件后缀限制
     */
    private List<String> suffix;

    /**
     * 备注
     */
    private String memo;

    public boolean supportSuffix(String suffix) {
        if (this.suffix == null || this.suffix.isEmpty()) {
            return true;
        }
        return this.suffix.contains(suffix);
    }
}
