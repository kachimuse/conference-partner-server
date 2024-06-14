package cn.edu.ecnu.conferencepartner.controller.user;

import cn.edu.ecnu.conferencepartner.common.context.UserContext;
import cn.edu.ecnu.conferencepartner.common.dto.ConferencePageQueryDTO;
import cn.edu.ecnu.conferencepartner.common.vo.ConferenceVO;
import cn.edu.ecnu.conferencepartner.common.vo.CommonResponseVO;
import cn.edu.ecnu.conferencepartner.common.vo.PageVO;
import cn.edu.ecnu.conferencepartner.service.IConferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户端 - 会议管理")
@RestController("userConferenceController")
@RequestMapping("/conferences")
@Slf4j
@RequiredArgsConstructor
public class ConferenceController {

    private final IConferenceService conferenceService;

    @Operation(summary = "根据条件分页查询会议")
    @GetMapping
    public CommonResponseVO<PageVO<ConferenceVO>> queryByPage(ConferencePageQueryDTO query) {
        log.info("用户分页查询会议：查询条件 = {}，用户id = {}", query, UserContext.get());
        return CommonResponseVO.success(conferenceService.queryByConditionAndPage(query));
    }

    @Operation(summary = "根据id查询会议")
    @GetMapping("/{id}")
    public CommonResponseVO<ConferenceVO> queryById(@PathVariable("id") Long id) {
        log.info("用户根据id查询会议：会议id = {}，用户id = {}", id, UserContext.get());
        return CommonResponseVO.success(conferenceService.queryById(id));
    }

    @Operation(summary = "根据id关注会议", security = {@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)})
    @PostMapping("/{id}/follow")
    public CommonResponseVO<?> followById(@PathVariable("id") Long id) {
        Long userId = UserContext.get();
        log.info("用户关注会议：会议id = {}，用户id = {}", id, userId);
        conferenceService.followByConferenceIdWithUserId(id, userId);
        return CommonResponseVO.success();
    }

    @Operation(summary = "根据id取消关注会议", security = {@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)})
    @DeleteMapping("/{id}/follow")
    public CommonResponseVO<?> cancelFollowById(@PathVariable("id") Long id) {
        Long userId = UserContext.get();
        log.info("用户取消关注会议：会议id = {}，用户id = {}", id, userId);
        conferenceService.cancelFollowByConferenceIdWithUserId(id, userId);
        return CommonResponseVO.success();
    }

}
