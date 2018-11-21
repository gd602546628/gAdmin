package com.example.blog.exceptionHandler;

import com.example.blog.constant.CommonCode;
import com.example.blog.responseUtil.ResponseVo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler({Exception.class, HttpMessageNotReadableException.class})
    @ResponseBody
    public ResponseVo handler(NativeWebRequest request, Exception e) {
        ResponseVo responseVo = new ResponseVo();
        String message = "";
        String code = "";
        if (e instanceof CommonException) { //参数校验异常
            message = ((CommonException) e).getMessage();
            code = ((CommonException) e).getErrCode();
        } else if (e instanceof HttpMessageNotReadableException) { //入参为空
            message = CommonCode.PARAMEEMP.getmessage();
            code = CommonCode.PARAMEEMP.getCode();
        } else if (e instanceof UnauthorizedException) {
            message = CommonCode.UNANAUTHORIZED.getmessage();
            code = CommonCode.UNANAUTHORIZED.getCode();
        } else if (e instanceof UnauthenticatedException) {
            message = CommonCode.UNLOGIN.getmessage();
            code = CommonCode.UNLOGIN.getCode();
        } else {
            message = CommonCode.UNKNOWERROR.getmessage();
            code = CommonCode.UNKNOWERROR.getCode();
            e.printStackTrace();
        }
        responseVo.setMessage(message);
        responseVo.setCode(code);
        return responseVo;
    }
}
