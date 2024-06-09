package cn.edu.ecnu.conferencepartner.service;

import cn.edu.ecnu.conferencepartner.common.dto.ConferenceDTO;
import cn.edu.ecnu.conferencepartner.common.dto.ConferencePageQueryDTO;
import cn.edu.ecnu.conferencepartner.common.po.Conference;
import cn.edu.ecnu.conferencepartner.common.vo.ConferenceVO;
import cn.edu.ecnu.conferencepartner.common.vo.PageVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会议表 服务类
 * </p>
 *
 * @author 龚奕玮
 * @since 2024-06-04
 */
public interface IConferenceService extends IService<Conference> {

    PageVO<ConferenceVO> queryByConditionAndPage(ConferencePageQueryDTO query);

    ConferenceVO queryById(Long id);

    void followByConferenceIdWithUserId(Long conferenceId, Long userId);

    void cancelFollowByConferenceIdWithUserId(Long conferenceId, Long userId);

    PageVO<ConferenceVO> queryFollowingConferencesByPageAndUserId(Long userId, ConferencePageQueryDTO query);

    void save(ConferenceDTO conferenceDTO);
}
