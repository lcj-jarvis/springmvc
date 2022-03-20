package com.mrlu.springmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-21 15:32
 *
 * @ResponseStatus注解对应ResponseStatusExceptionResolver
 *  这个注解既可以使用在类上面，也可以使用在方法的上面。可以用来设置响应的状态码页面
 */


@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "密码错误")
public class PasswordException extends MyUserException{
    public PasswordException() {
    }

    public PasswordException(String message) {
        super(message);
    }
}
