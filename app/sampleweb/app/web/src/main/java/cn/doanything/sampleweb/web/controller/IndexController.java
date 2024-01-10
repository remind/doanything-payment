package cn.doanything.sampleweb.web.controller;

import cn.doanything.framework.api.sequence.SequenceService;
import cn.doanything.sample.facade.HiFacade;
import cn.doanything.sampleweb.web.WebConfig;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @DubboReference
    private HiFacade hiFacade;

    @Autowired
    private WebConfig webConfig;

    @Autowired
    private SequenceService sequenceService;

    @RequestMapping("/hi")
    public String hi() {
        return hiFacade.say() + "__" + webConfig.isUseLocalCache();
    }
    @RequestMapping("/seq")
    public String sequence() {
        return String.valueOf(sequenceService.getNext("testSeq"));
    }
}
