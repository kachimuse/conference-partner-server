package cn.edu.ecnu.conferencepartner.service.impl;

import cn.edu.ecnu.conferencepartner.common.dto.ConferenceDTO;
import cn.edu.ecnu.conferencepartner.common.dto.ConferencePageQueryDTO;
import cn.edu.ecnu.conferencepartner.common.exception.DataNotFoundException;
import cn.edu.ecnu.conferencepartner.common.po.Conference;
import cn.edu.ecnu.conferencepartner.common.po.UserConference;
import cn.edu.ecnu.conferencepartner.common.vo.ConferenceVO;
import cn.edu.ecnu.conferencepartner.mapper.ConferenceMapper;
import cn.edu.ecnu.conferencepartner.common.vo.PageVO;
import cn.edu.ecnu.conferencepartner.mapper.UserConferenceMapper;
import cn.edu.ecnu.conferencepartner.service.IConferenceService;
import cn.edu.ecnu.conferencepartner.common.utils.PageUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 会议表 服务实现类
 * </p>
 *
 * @author 龚奕玮
 * @since 2024-06-04
 */
@Service
@RequiredArgsConstructor
public class ConferenceServiceImpl extends ServiceImpl<ConferenceMapper, Conference> implements IConferenceService {

    private final UserConferenceMapper userConferenceMapper;

    @Override
    public PageVO<ConferenceVO> queryByConditionAndPage(ConferencePageQueryDTO query) {
        Page<Conference> page = PageUtil.toMybatisPlusPage(query);
        String name = query.getName();
        Year year = query.getYear() != null ? Year.of(query.getYear()) : null;
        Integer grade = query.getGrade();

        page = lambdaQuery()
                .eq(year != null, Conference::getYear, year)
                .eq(grade != null, Conference::getGrade, grade)
                .and(name != null,
                        wrapper -> wrapper.like(Conference::getName, name).or().like(Conference::getAbbrev, name))
                .page(page);

        return PageVO.of(page, ConferenceVO.class);
    }

    @Override
    public ConferenceVO queryById(Long id) {
        Conference conference = getById(id);
        if (conference == null) {
            throw new DataNotFoundException("会议不存在");
        }
        return BeanUtil.copyProperties(conference, ConferenceVO.class);
    }

    @Override
    public void followByConferenceIdWithUserId(Long conferenceId, Long userId) {
        UserConference userConference = UserConference.builder()
                .userId(userId)
                .conferenceId(conferenceId)
                .createTime(LocalDateTime.now())
                .build();
        userConferenceMapper.insert(userConference);
    }

    @Override
    public void cancelFollowByConferenceIdWithUserId(Long conferenceId, Long userId) {
        Map<String, Object> map = new HashMap<>() {{
            put("user_id", userId);
            put("conference_id", conferenceId);
        }};
        userConferenceMapper.deleteByMap(map);
    }

    @Override
    public PageVO<ConferenceVO> queryFollowingConferencesByPageAndUserId(Long userId, ConferencePageQueryDTO query) {
        Page<Conference> page = PageUtil.toMybatisPlusPage(query);

        page = baseMapper.selectPageByUserId(page, query, userId);
        return PageVO.of(page, ConferenceVO.class);
    }

    @Override
    public void save(ConferenceDTO conferenceDTO) {
        Conference conference = BeanUtil.copyProperties(conferenceDTO, Conference.class, "year");
        conference.setYear(Year.of(conferenceDTO.getYear()));
        conference.setCreateTime(LocalDateTime.now());
        this.save(conference);
    }
}
