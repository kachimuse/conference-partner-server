package cn.edu.ecnu.conferencepartner.mapper;

import cn.edu.ecnu.conferencepartner.common.po.UserConference;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户关注会议表 Mapper 接口
 * </p>
 *
 * @author 龚奕玮
 * @since 2024-06-07
 */
@Mapper
public interface UserConferenceMapper extends BaseMapper<UserConference> {

}
