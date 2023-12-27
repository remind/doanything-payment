package cn.doanything.framework.scheduler.local;

import cn.doanything.framework.scheduler.model.HandlerInfo;
import cn.doanything.framework.scheduler.model.LockModel;
import lombok.Data;

@Data
public class LocalHandlerInfo extends HandlerInfo {

    /**
     * 锁模式
     */
    private LockModel lockModel;

}
