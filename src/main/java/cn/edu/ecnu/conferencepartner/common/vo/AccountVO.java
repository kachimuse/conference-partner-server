package cn.edu.ecnu.conferencepartner.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Schema(description = "用户账户视图对象")
@Data
@Builder
public class AccountVO {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "用户名")
    private String name;

    @Schema(description = "机构")
    private String institution;

    @Schema(description = "注册日期")
    private LocalDate registerDate;
}
