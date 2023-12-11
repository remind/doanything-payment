package cn.doanything.member;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * member启动类
 * @author wxj
 * 2023/12/10
 */
@SpringBootApplication
@Slf4j
@EnableDubbo
@MapperScan("cn.doanything.member.infrastructure.persistence.mapper")
public class MemberBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberBootstrapApplication.class);
        log.info("member start success");
    }
}
