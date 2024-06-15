package cn.edu.ecnu.conferencepartner.common.vo;

import cn.edu.ecnu.conferencepartner.common.enums.CodeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;

@Schema(description = "统一响应数据视图对象")
@Value
public class CommonResponseVO<T> implements Serializable {

    @Schema(description = "响应状态码")
    private int code;

    @Schema(description = "响应消息")
    private String msg;

    @Schema(description = "响应数据")
    private T data;

    public static <T> CommonResponseVO<T> success() {
        return new CommonResponseVO<>(CodeType.SUCCESS.getValue(), CodeType.SUCCESS.getDescription(), null);
    }

    public static <T> CommonResponseVO<T> success(T data) {
        return new CommonResponseVO<>(CodeType.SUCCESS.getValue(), CodeType.SUCCESS.getDescription(), data);
    }

    public static <T> CommonResponseVO<T> error() {
        return new CommonResponseVO<>(CodeType.ERROR.getValue(), CodeType.ERROR.getDescription(), null);
    }

    public static <T> CommonResponseVO<T> error(String msg) {
        return new CommonResponseVO<>(CodeType.ERROR.getValue(), msg, null);
    }

    public static <T> CommonResponseVO<T> of(int code, String msg) {
        return new CommonResponseVO<>(code, msg, null);
    }

    public static <T> CommonResponseVO<T> of(int code, String msg, T data) {
        return new CommonResponseVO<>(code, msg, data);
    }

    public static <T> CommonResponseVO<T> of(CodeType type) {
        return new CommonResponseVO<>(type.getValue(), type.getDescription(), null);
    }

    public static <T> CommonResponseVO<T> of(CodeType type, T data) {
        return new CommonResponseVO<>(type.getValue(), type.getDescription(), data);
    }
}
