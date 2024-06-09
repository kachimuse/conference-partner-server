package cn.edu.ecnu.conferencepartner.common.po;

import cn.edu.ecnu.conferencepartner.common.enums.UserStatusType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户 PO
 *
 * @author 龚奕玮
 * @since 2024-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 用户密码
     */
    @TableField("password")
    private String password;

    /**
     * 用户名
     */
    @TableField("name")
    private String name;

    /**
     * 用户所属机构
     */
    @TableField("institution")
    private String institution;

    /**
     * 用户状态 1为正常 0为冻结
     */
    @TableField("status")
    private UserStatusType status;

    /**
     * 是否为管理员
     */
    @TableField("admin")
    private Boolean isAdmin;

    /**
     * 用户创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 用户信息最后修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 用户最后登录时间
     */
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;


}
