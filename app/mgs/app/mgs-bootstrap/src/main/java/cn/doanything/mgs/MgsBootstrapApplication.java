package cn.doanything.mgs;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wxj
 * 2023/12/14
 */
@SpringBootApplication
@Slf4j
@EnableDubbo
public class MgsBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(MgsBootstrapApplication.class);
        log.info("mgs start success");
    }
}
