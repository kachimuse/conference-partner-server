package cn.edu.ecnu.conferencepartner.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 会议等级 类型
 * @author 龚奕玮
 * @since 2024-06-09
 */
@Getter
public enum ConferenceGradeType {
    OTHERS(0),
    CCF_C(1),
    CCF_B(2),
    CCF_A(3);

    @EnumValue
    private final int value;

    ConferenceGradeType(int value) {
        this.value = value;
    }
}
