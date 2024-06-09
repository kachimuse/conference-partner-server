package cn.edu.ecnu.conferencepartner.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

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
