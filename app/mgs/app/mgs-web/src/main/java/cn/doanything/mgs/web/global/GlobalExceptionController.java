package cn.doanything.mgs.web.global;

import cn.doanything.commons.exceptions.BizException;
import cn.doanything.commons.response.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author wxj
 * 2023/12/14
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ResponseResult exceptionHandler(HttpServletRequest request, Exception e) {
        if (e instanceof BizException) {
            log.info("请求异常,异常信息={}", e.getMessage());
        } else {
            log.error("请求异常,url={},异常信息={}", request.getRequestURI(), e.getMessage(), e);
        }
        return ResponseResult.fail(e.getMessage());
    }
}
