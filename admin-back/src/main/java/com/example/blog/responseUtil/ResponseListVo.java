package com.example.blog.responseUtil;

import com.github.pagehelper.PageInfo;

public class ResponseListVo <T>{
    private String code = "000000";
    private String message = "成功";
    private ListVo data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ListVo getData() {
        return data;
    }

    public void setData(PageInfo pageInfo) {
        ListVo<T> listVo = new ListVo<>();

        listVo.setPageNum(pageInfo.getPageNum());
        listVo.setTotalPage(pageInfo.getPages());
        listVo.setPageSize(pageInfo.getPageSize());
        listVo.setList(pageInfo.getList());

        this.data = listVo;
    }
}
