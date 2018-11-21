package com.example.blog.exceptionHandler;

import com.example.blog.constant.CommonCode;

public class CommonException extends RuntimeException {
    private String errCode;

    public CommonException(String errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

    public CommonException(CommonCode commonCode) {
        super(commonCode.getmessage());
        this.errCode = commonCode.getCode();
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}
