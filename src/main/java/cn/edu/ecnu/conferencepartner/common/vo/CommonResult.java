package cn.edu.ecnu.conferencepartner.common.vo;

import cn.edu.ecnu.conferencepartner.common.enums.CodeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;

@Schema(name = "统一返回结果")
@Value
public class CommonResult<T> implements Serializable {

    @Schema(description = "状态码")
    private int code;

    @Schema(description = "返回消息")
    private String msg;

    @Schema(description = "返回数据")
    private T data;

    public static <T> CommonResult<T> success() {
        return new CommonResult<>(CodeType.SUCCESS.getValue(), "success", null);
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(CodeType.SUCCESS.getValue(), "success", data);
    }

    public static <T> CommonResult<T> error(String msg) {
        return new CommonResult<>(CodeType.ERROR.getValue(), msg, null);
    }
}
