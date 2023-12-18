package cn.doanything.framework.dal.mybatis;

import cn.doanything.framework.dal.mybatis.ext.ExtSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wxj
 * 2023/12/18
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public ISqlInjector sqlInjector() {
        return new ExtSqlInjector();
    }
}
