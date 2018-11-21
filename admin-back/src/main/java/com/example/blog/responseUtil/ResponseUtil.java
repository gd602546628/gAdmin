package com.example.blog.responseUtil;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class ResponseUtil {
    public static ResponseVo responseVo(Object data, String code, String message) {
        ResponseVo vo = new ResponseVo();
        vo.setCode(code);
        vo.setMessage(message);
        vo.setData(data);
        return vo;
    }

    public static <T> ResponseListVo responseListVo(PageInfo pageInfo,
                                                String code,
                                                String message
    ) {
        ResponseListVo<T> listVo = new ResponseListVo();
        listVo.setData(pageInfo);
        listVo.setCode(code);
        listVo.setMessage(message);
        return listVo;
    }
}
