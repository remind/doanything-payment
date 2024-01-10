package cn.doanything.basic.facade.mns;

import cn.doanything.basic.facade.mns.dto.NotifyMessageRequest;
import cn.doanything.commons.response.ResponseResult;

/**
 * @author wxj
 * 2024/1/9
 */
public interface MessageNotifyFacade {

    /**
     * 发送消息
     * @param request
     * @return
     */
    ResponseResult<String> send(NotifyMessageRequest request);

}
