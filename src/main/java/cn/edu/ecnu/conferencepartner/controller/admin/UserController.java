package cn.edu.ecnu.conferencepartner.controller.admin;

import cn.edu.ecnu.conferencepartner.common.dto.ConferencePageQueryDTO;
import cn.edu.ecnu.conferencepartner.common.dto.UserPageQueryDTO;
import cn.edu.ecnu.conferencepartner.common.vo.CommonResponseVO;
import cn.edu.ecnu.conferencepartner.common.vo.ConferenceVO;
import cn.edu.ecnu.conferencepartner.common.vo.PageVO;
import cn.edu.ecnu.conferencepartner.common.vo.UserVO;
import cn.edu.ecnu.conferencepartner.service.IConferenceService;
import cn.edu.ecnu.conferencepartner.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@Tag(name = "管理端 - 用户管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@RestController
@RequestMapping("/admin/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final IConferenceService conferenceService;

    @Operation(summary = "根据条件分页查询用户")
    @GetMapping
    public CommonResponseVO<PageVO<UserVO>> queryByConditionAndPage(UserPageQueryDTO query) {
        log.info("根据条件分页查询用户：{}", query);
        return CommonResponseVO.success(userService.queryByConditionAndPage(query));
    }

    @Operation(summary = "根据id查询指定用户")
    @GetMapping("/{id}")
    public CommonResponseVO<UserVO> queryById(@PathVariable("id") Long id) {
        log.info("根据id查询指定用户：{}", id);
        return CommonResponseVO.success(userService.queryById(id));
    }

    @Operation(summary = "冻结用户")
    @PutMapping("/{id}/freeze")
    public CommonResponseVO<?> freezeById(@PathVariable("id") Long id) {
        log.info("冻结用户：{}", id);
        userService.freezeById(id);
        return CommonResponseVO.success();
    }

    @Operation(summary = "解除用户冻结")
    @PutMapping("/{id}/unfreeze")
    public CommonResponseVO<?> unfreezeById(@PathVariable("id") Long id) {
        log.info("解除用户冻结：{}", id);
        userService.unfreezeById(id);
        return CommonResponseVO.success();
    }

    @Operation(summary = "分页查询用户关注的会议")
    @GetMapping("/{id}/follow")
    public CommonResponseVO<PageVO<ConferenceVO>> queryFollowingConferencesByPageAndUserId(
            @PathVariable("id") Long id, ConferencePageQueryDTO query) {
        log.info("分页查询用户关注的会议：{}, {}", id, query);
        return CommonResponseVO.success(conferenceService.queryFollowingConferencesByPageAndUserId(id, query));
    }
}
