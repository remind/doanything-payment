package cn.doanything.mgs.web.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.doanything.commons.response.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录
 * @author wxj
 * 2023/12/30
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/web")
    public ResponseResult<String> login(String username, String password) {
        StpUtil.login(10001);
        return ResponseResult.success();
    }

    @RequestMapping("/app")
    public ResponseResult loginApp(String username, String password) {
        StpUtil.login(10001,"APP");
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return ResponseResult.success(tokenInfo);
    }

    /**
     * 判断是否登录
     * @return
     */
    @RequestMapping("/isLogin")
    public ResponseResult<Boolean> isLogin() {
        return ResponseResult.success(StpUtil.isLogin());
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping("/logout")
    public ResponseResult<String> logout() {
        StpUtil.logout();
        return ResponseResult.success();
    }
}
