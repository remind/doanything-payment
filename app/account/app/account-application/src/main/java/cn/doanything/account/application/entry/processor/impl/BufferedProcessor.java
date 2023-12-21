package cn.doanything.account.application.entry.processor.impl;

import cn.doanything.account.application.entry.EntryContext;
import cn.doanything.account.application.entry.processor.AccountEntryProcessor;
import cn.doanything.account.domain.repository.BufferedDetailRepository;
import jakarta.annotation.Resource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * 2023/12/21
 */
@Component
@Order(2)
public class BufferedProcessor implements AccountEntryProcessor {

    @Resource
    private BufferedDetailRepository bufferedDetailRepository;

    @Override
    public void process(EntryContext entryContext) {
        bufferedDetailRepository.store(entryContext.getBufferedDetails());
    }
}
