package cn.doanything.sample;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@EnableDubbo
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class);
        log.info("sample start success");
    }
}
