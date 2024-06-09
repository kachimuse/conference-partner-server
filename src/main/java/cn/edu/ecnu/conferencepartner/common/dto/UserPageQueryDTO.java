package cn.edu.ecnu.conferencepartner.common.dto;

import cn.edu.ecnu.conferencepartner.common.enums.UserStatusType;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Schema(name = "用户分页查询请求数据传输对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPageQueryDTO extends PageQueryDTO {

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "用户名")
    private String name;

    @Schema(description = "用户所属机构")
    private String institution;

    @Schema(description = "用户状态")
    private Integer status;

    @Schema(description = "用户是否为管理员")
    private Boolean isAdmin;

    @Schema(description = "用户最后登录日期起始范围")
    private LocalDate lastLoginDateBegin;

    @Schema(description = "用户最后登录日期结束范围")
    private LocalDate lastLoginDateEnd;
}
