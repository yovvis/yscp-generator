package com.yovvis.maker.meta;

/**
 * Meta的自定义异常
 *
 * @author yovvis
 * @date 2023/12/24
 */
public class MetaException extends RuntimeException {
    public MetaException(String message) {
        super(message);
    }

    public MetaException(String message, Throwable cause) {
        super(message, cause);
    }
}
