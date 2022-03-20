package com.mrlu.springmvc.exception;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-17 14:31
 */
public class AgeException extends MyUserException{
    public AgeException() {
    }

    public AgeException(String message) {
        super(message);
    }
}
