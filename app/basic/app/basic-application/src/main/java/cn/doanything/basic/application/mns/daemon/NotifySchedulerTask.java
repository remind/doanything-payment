package cn.doanything.basic.application.mns.daemon;

import cn.doanything.basic.application.mns.MessageNotifyService;
import cn.doanything.basic.domain.BasicConstants;
import cn.doanything.basic.domain.mns.MessageDetail;
import cn.doanything.basic.domain.mns.repository.MessageDetailRepository;
import cn.doanything.framework.scheduler.handler.SchedulerTaskHandler;
import cn.doanything.framework.scheduler.handler.annotation.TaskHandler;
import cn.doanything.framework.scheduler.model.LockModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wxj
 * 2024/1/10
 */
@TaskHandler(taskType = BasicConstants.DEFAULT_TASK_TYPE, lockModel = LockModel.STRICT)
@Slf4j
public class NotifySchedulerTask implements SchedulerTaskHandler {

    @Autowired
    private MessageNotifyService messageNotifyService;

    @Autowired
    private MessageDetailRepository messageDetailRepository;

    @Override
    public boolean handle(String taskId, String taskType, String bizId, String param) {
        MessageDetail messageDetail = messageDetailRepository.lock(bizId);
        messageNotifyService.send(messageDetail);
        return true;
    }

    @Override
    public void failHandle(String taskId, String taskType, String bizId, String param) {

    }
}
