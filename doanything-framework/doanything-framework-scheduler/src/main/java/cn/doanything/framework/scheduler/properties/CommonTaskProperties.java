package cn.doanything.framework.scheduler.properties;

import cn.doanything.framework.scheduler.CommonTaskConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置属性
 */
@ConfigurationProperties(CommonTaskConstants.PREFIX)
@Component
@Data
public class CommonTaskProperties {

    /**
     * 单次加载条数
     */
    private int loadCount = 100;

    /**
     * 执行间隔时间，单位为秒
     */
    private int executeIntervalSeconds = 60;

    /**
     * 最大执行次数
     */
    private int maxExecuteCount = 10;

    /**
     * 任务最大执行时间，单位分钟
     */
    private int maxExecuteMinutes = 30;

    /**
     * 并发执行线程数
     */
    private int executeThread = 5;


}
