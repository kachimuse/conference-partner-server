package cn.edu.ecnu.conferencepartner.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "用户账户数据传输对象")
@Data
public class AccountDTO {

    @Schema(description = "用户名")
    private String name;

    @Schema(description = "机构")
    private String institution;
}
