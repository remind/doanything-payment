package cn.doanything.sampleweb.web;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 2023/12/9
 *
 * @author wxj
 * @version V1.0
 * @description:
 */
@NacosConfigurationProperties(dataId = "${spring.application.name}", autoRefreshed = true)
@Configuration
public class WebConfig {

    private boolean useLocalCache;

    public boolean isUseLocalCache() {
        return useLocalCache;
    }

    public void setUseLocalCache(boolean useLocalCache) {
        this.useLocalCache = useLocalCache;
    }
}
