package cn.edu.ecnu.conferencepartner.mapper;

import cn.edu.ecnu.conferencepartner.common.dto.ConferencePageQueryDTO;
import cn.edu.ecnu.conferencepartner.common.po.Conference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 会议表 Mapper 接口
 * </p>
 *
 * @author 龚奕玮
 * @since 2024-06-04
 */
@Mapper
public interface ConferenceMapper extends BaseMapper<Conference> {

    Page<Conference> selectPageByUserId(Page<Conference> page, ConferencePageQueryDTO query, Long userId);
}
