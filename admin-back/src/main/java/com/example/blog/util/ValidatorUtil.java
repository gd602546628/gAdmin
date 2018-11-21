package com.example.blog.util;

import com.example.blog.constant.CommonCode;
import com.example.blog.exceptionHandler.CommonException;

import javax.validation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidatorUtil {
    public static <T> void validate(T t) throws CommonException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);

        List<String> messageList = new ArrayList<>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getMessage());
        }

        if (messageList.size() > 0) {
            throw new CommonException(CommonCode.PARAMERROR.getCode(), messageList.get(0));
        }
    }
}
