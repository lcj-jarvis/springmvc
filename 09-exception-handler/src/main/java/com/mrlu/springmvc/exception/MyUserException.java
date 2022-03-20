package com.mrlu.springmvc.exception;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-17 14:31
 */
public class MyUserException extends RuntimeException{
    public MyUserException() {
        super();
    }

    public MyUserException(String message) {
        super(message);
    }
}
