package cn.edu.ecnu.conferencepartner.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Value;

@Schema(name = "用户登录请求")
@Data
public class UserLoginDTO {
    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "密码")
    private String password;
}
