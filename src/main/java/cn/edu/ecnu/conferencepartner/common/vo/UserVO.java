package cn.edu.ecnu.conferencepartner.common.vo;

import cn.edu.ecnu.conferencepartner.common.enums.UserStatusType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Schema(name = "用户视图对象")
@Data
@Builder
public class UserVO {

    @Schema(description = "用户id")
    private Long id;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "用户名")
    private String name;

    @Schema(description = "用户所属机构")
    private String institution;

    @Schema(description = "用户状态")
    private UserStatusType status;

    @Schema(description = "用户是否有管理员权限")
    private Boolean isAdmin;

    @Schema(description = "用户最后登录日期")
    private LocalDate lastLoginDate;
}
