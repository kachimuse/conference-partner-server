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
    SUCCESS(0, "成功"),
    ERROR(1, "失败");

    @EnumValue
    private final int value;

    private final String description;

    CodeType(int value, String description) {
        this.value = value;
        this.description = description;
    }
}
