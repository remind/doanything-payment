package cn.doanything.mgs.web.global;

import cn.doanything.commons.response.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常处理
 * @author wxj
 * 2023/12/14
 */
@RestController
public class GlobalErrorController implements ErrorController {

//    @RequestMapping("/error")
//    public ResponseResult error(HttpServletRequest request) {
//        return ResponseResult.fail();
//    }

}
