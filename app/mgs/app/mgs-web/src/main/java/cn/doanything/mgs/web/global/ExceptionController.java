package cn.doanything.mgs.web.global;

import cn.doanything.commons.response.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author wxj
 * 2023/12/14
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = {Exception.class})
    public ResponseResult exceptionHandler(HttpServletRequest request, Exception e) {
        return ResponseResult.fail("未知异常1");
    }
}
