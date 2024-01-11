package cn.doanything.framework.scheduler.autoconfigure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wxj
 * 2024/1/11
 */
@Configuration
@ComponentScan("cn.doanything.framework.scheduler")
@MapperScan("cn.doanything.framework.scheduler.repository.mybatis")
public class SchedulerConfigure {
}
