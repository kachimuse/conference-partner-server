package cn.edu.ecnu.conferencepartner.common.exception;

/**
 * 业务异常
 * @author 龚奕玮
 * @since 2024-06-09
 */
public class BaseException extends RuntimeException {

    public BaseException(String msg) {
        super(msg);
    }
}
