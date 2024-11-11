package com.jsz.maker.meta;

/**
 * @Title: MetaException
 * @Author jsz
 * @Package com.jsz.maker.meta
 * @Date 2024/11/11 15:24
 * @description: 自定义异常
 */
public class MetaException extends RuntimeException {
    public MetaException(String message, Throwable cause) {
        super(message, cause);
    }



    public MetaException(String message) {
        super(message);
    }
}
