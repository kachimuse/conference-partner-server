package cn.edu.ecnu.conferencepartner.handler;

import cn.edu.ecnu.conferencepartner.common.exception.BaseException;
import cn.edu.ecnu.conferencepartner.common.vo.CommonResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * 全局异常处理器
 * @author 龚奕玮
 * @since 2024-04-06
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 默认处理
     */
    @ExceptionHandler
    public CommonResponseVO<?> handleAllExceptions(Exception ex) {
        log.error(ex.getMessage());
        ex.printStackTrace();
        return CommonResponseVO.error("发生未知错误，请联系管理员");
    }

    /**
     * 处理业务异常
     */
    @ExceptionHandler
    public CommonResponseVO<?> handleBaseExceptions(BaseException ex) {
        log.error(ex.getMessage());
        return CommonResponseVO.error(ex.getMessage());
    }

    /**
     * 处理SQL异常
     */
    @ExceptionHandler
    public CommonResponseVO<?> handleSQLExceptions(SQLException ex) {
        log.error(ex.getMessage());
        if (ex.getMessage().contains("Duplicate entry")) {
            return CommonResponseVO.error("注册邮箱已存在，请直接登录");
        }
        return CommonResponseVO.error("发生未知错误，请联系管理员");
    }

}
