package cn.edu.ecnu.conferencepartner.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 响应状态码 类型
 * @author 龚奕玮
 * @since 2024-06-09
 */
@Getter
public enum CodeType {
    SUCCESS(HttpStatus.OK.value(), "请求成功"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "请求参数错误"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "未授权"),
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), "权限不足"),
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "请求资源不存在"),
    ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "发生未知异常");

    @EnumValue
    private final int value;

    private final String description;

    CodeType(int value, String description) {
        this.value = value;
        this.description = description;
    }
}
