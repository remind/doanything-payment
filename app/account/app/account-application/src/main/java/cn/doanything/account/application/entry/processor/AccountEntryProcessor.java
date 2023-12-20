package cn.doanything.account.application.entry.processor;

import cn.doanything.account.application.entry.EntryContext;

/**
 * @author wxj
 * 2023/12/20
 */
public interface AccountEntryProcessor {

    void process(EntryContext entryContext);
}
