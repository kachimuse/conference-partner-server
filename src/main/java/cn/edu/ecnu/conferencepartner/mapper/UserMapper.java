package cn.edu.ecnu.conferencepartner.mapper;

import cn.edu.ecnu.conferencepartner.common.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 龚奕玮
 * @since 2024-06-04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
