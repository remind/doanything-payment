package cn.doanything.framework.sequence.autoconfigure;

import cn.doanything.framework.api.sequence.SequenceService;
import cn.doanything.framework.sequence.SequenceInnerService;
import cn.doanything.framework.sequence.SequenceServiceImpl;
import cn.doanything.framework.sequence.repository.JdbcSequenceRepository;
import cn.doanything.framework.sequence.repository.SequenceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 序列自动配置
 * @author wxj
 * 2023/12/10
 */
@Configuration
public class SequenceConfigure {

    @Bean
    public SequenceRepository sequenceDAO(JdbcTemplate jdbcTemplate) {
        return new JdbcSequenceRepository(jdbcTemplate);
    }
    @Bean
    public SequenceInnerService sequenceInnerService(ThreadPoolTaskExecutor threadPoolTaskExecutor, SequenceRepository sequenceRepository, TransactionTemplate transactionTemplate) {
        return new SequenceInnerService(threadPoolTaskExecutor, sequenceRepository, transactionTemplate);
    }
    @Bean
    public SequenceService sequenceService(SequenceInnerService sequenceInnerService) {
        return new SequenceServiceImpl(sequenceInnerService);
    }
}
