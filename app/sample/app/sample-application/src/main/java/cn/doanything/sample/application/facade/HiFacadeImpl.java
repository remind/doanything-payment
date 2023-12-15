package cn.doanything.sample.application.facade;

import cn.doanything.sample.facade.HiFacade;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class HiFacadeImpl implements HiFacade {
    @Override
    public String say() {
        return "hello";
    }
}
