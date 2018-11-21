package com.example.blog.constant;

public enum CommonCode {
    SUCCESS("000000", "SUCCESS"),
    PARAMERROR("000001", "参数有误"),
    PARAMEEMP("000002", "接口入参不能为空"),
    UNKNOWERROR("000003", "未知错误"),
    DISABLETOKEN("000004", "token失效"),
    UNKONEUSER("000005", "用户名或密码错误"),
    UNANAUTHORIZED("000006", "没有权限"),
    UNLOGIN("000007", "没有登录");
    private String code;

    private String message;

    CommonCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getmessage() {
        return message;
    }

    public void setmessage(String message) {
        this.message = message;
    }
}
