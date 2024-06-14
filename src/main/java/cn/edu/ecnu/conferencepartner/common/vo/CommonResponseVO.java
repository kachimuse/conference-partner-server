package cn.edu.ecnu.conferencepartner.common.vo;

import cn.edu.ecnu.conferencepartner.common.enums.CodeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Schema(description = "统一响应数据视图对象")
@Value
public class CommonResponseVO<T> implements Serializable {

    @Schema(description = "响应状态码 0为成功 1为失败")
    private int code;

    @Schema(description = "响应消息")
    private String msg;

    @Schema(description = "响应数据")
    private T data;

    public static <T> CommonResponseVO<T> success() {
        return new CommonResponseVO<>(CodeType.SUCCESS.getValue(), "success", null);
    }

    public static <T> CommonResponseVO<T> success(T data) {
        return new CommonResponseVO<>(CodeType.SUCCESS.getValue(), "success", data);
    }

    public static <T> CommonResponseVO<T> error(String msg) {
        return new CommonResponseVO<>(CodeType.ERROR.getValue(), msg, null);
    }
}
