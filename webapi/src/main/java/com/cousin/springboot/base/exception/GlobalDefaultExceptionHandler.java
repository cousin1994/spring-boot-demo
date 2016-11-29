package com.cousin.springboot.base.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常捕捉
 *
 * @author cousin
 * @Created 2016/11/27 18:32
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {


    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    public String fileSizeLimitHandler(HttpServletRequest request, Exception e) {
        return "上传文件问题" + e.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defaultErrorHandler(HttpServletRequest request, Exception e) {
        return e.getMessage();
    }
}
