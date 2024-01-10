package cn.doanything.framework.scheduler.autoconfigure;

import cn.doanything.framework.scheduler.handler.HandlerContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * 2024/1/10
 */
@Component
@Slf4j
public class StartupInit implements ApplicationRunner {

    @Autowired
    private HandlerContainer handlerContainer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            handlerContainer.initHandler();
        } catch (Exception e) {
            log.error("调度任务启动加载异常", e);
            throw e;
        }
    }

}
