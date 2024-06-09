package cn.edu.ecnu.conferencepartner.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 用户状态 类型
 * @author 龚奕玮
 * @since 2024-06-09
 */
@Getter
public enum UserStatusType {
    FREEZED(0, "冻结"),
    NORMAL(1, "正常");

    @EnumValue
    private final int value;

    private final String description;

    UserStatusType(int value, String description) {
        this.value = value;
        this.description = description;
    }
}
