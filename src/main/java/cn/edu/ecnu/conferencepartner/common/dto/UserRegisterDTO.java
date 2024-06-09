package cn.edu.ecnu.conferencepartner.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "用户注册请求数据传输对象")
@Data
public class UserRegisterDTO {

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "用户名")
    private String name;

    @Schema(description = "所属机构")
    private String institution;
}
