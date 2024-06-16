package cn.edu.ecnu.conferencepartner.handler;

import cn.edu.ecnu.conferencepartner.common.enums.CodeType;
import cn.edu.ecnu.conferencepartner.common.exception.BusinessException;
import cn.edu.ecnu.conferencepartner.common.exception.DataNotFoundException;
import cn.edu.ecnu.conferencepartner.common.exception.ForbiddenException;
import cn.edu.ecnu.conferencepartner.common.exception.UnauthorizedException;
import cn.edu.ecnu.conferencepartner.common.vo.CommonResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * @author 龚奕玮
 * @since 2024-04-06
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResponseVO<?> handleDuplicateKeyException(DuplicateKeyException ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return CommonResponseVO.error("注册邮箱已存在，请直接登录");
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResponseVO<?> handleDataNotFoundException(DataNotFoundException ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return CommonResponseVO.of(CodeType.NOT_FOUND.getValue(), ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResponseVO<?> handleUnauthorizedException(UnauthorizedException ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return CommonResponseVO.of(CodeType.UNAUTHORIZED.getValue(), ex.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public CommonResponseVO<?> handleForbiddenException(ForbiddenException ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return CommonResponseVO.of(CodeType.FORBIDDEN.getValue(), ex.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponseVO<?> handleNullPointerException(NullPointerException ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return CommonResponseVO.of(CodeType.BAD_REQUEST);
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponseVO<?> handleBadSqlGrammarException(BadSqlGrammarException ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return CommonResponseVO.of(CodeType.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public CommonResponseVO<?> handleBusinessExceptions(BusinessException ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return CommonResponseVO.error(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public CommonResponseVO<?> handleAllExceptions(Exception ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return CommonResponseVO.error();
    }

}
