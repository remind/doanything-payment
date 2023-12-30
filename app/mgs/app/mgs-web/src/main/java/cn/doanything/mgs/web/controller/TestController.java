package cn.doanything.mgs.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wxj
 * 2023/12/29
 */
@RestController
@RequestMapping("/test/")
public class TestController {

    @RequestMapping("exception")
    public String exception() {
        throw new RuntimeException("测试异常");
    }
}
