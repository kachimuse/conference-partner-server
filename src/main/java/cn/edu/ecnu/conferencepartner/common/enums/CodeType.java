package cn.edu.ecnu.conferencepartner.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 响应状态码 类型
 * @author 龚奕玮
 * @since 2024-06-09
 */
@Getter
public enum CodeType {
    ERROR(0, "失败"),
    SUCCESS(1, "成功");

    @EnumValue
    private final int value;

    private final String description;

    CodeType(int value, String description) {
        this.value = value;
        this.description = description;
    }
}
