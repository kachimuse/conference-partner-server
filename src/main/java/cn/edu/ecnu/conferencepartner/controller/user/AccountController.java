package cn.edu.ecnu.conferencepartner.controller.user;

import cn.edu.ecnu.conferencepartner.common.context.UserContext;
import cn.edu.ecnu.conferencepartner.common.dto.AccountDTO;
import cn.edu.ecnu.conferencepartner.common.dto.ConferencePageQueryDTO;
import cn.edu.ecnu.conferencepartner.common.dto.PasswordDTO;
import cn.edu.ecnu.conferencepartner.common.vo.*;
import cn.edu.ecnu.conferencepartner.service.IAccountService;
import cn.edu.ecnu.conferencepartner.service.IConferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户端 - 账户管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@RestController
@RequestMapping("/account")
@Slf4j
@RequiredArgsConstructor
public class AccountController {

    private final IAccountService accountService;
    private final IConferenceService conferenceService;

    @Operation(summary = "修改密码")
    @PutMapping("/password")
    public CommonResponseVO<?> changePassword(@RequestBody PasswordDTO passwordDTO) {
        Long userId = UserContext.get();
        log.info("用户修改密码：修改信息 = {}，用户id = {}", passwordDTO, userId);
        accountService.changePassword(userId, passwordDTO);
        return CommonResponseVO.success();
    }

    @Operation(summary = "查询个人信息")
    @GetMapping()
    public CommonResponseVO<AccountVO> queryInfo() {
        Long userId = UserContext.get();
        log.info("用户查询个人信息：用户id = {}", userId);
        return CommonResponseVO.success(accountService.queryById(userId));
    }

    @Operation(summary = "修改个人信息")
    @PutMapping("/edit")
    public CommonResponseVO<?> modifyInfo(@RequestBody AccountDTO accountDTO) {
        Long userId = UserContext.get();
        log.info("用户修改个人信息：修改信息 = {}，用户id = {}", accountDTO, userId);
        accountService.modifyInfo(userId, accountDTO);
        return CommonResponseVO.success();
    }

    @Operation(summary = "分页查询关注的会议")
    @GetMapping("/conferences")
    public CommonResponseVO<PageVO<ConferenceVO>> queryFollowingConferencesByPage(ConferencePageQueryDTO query) {
        Long userId = UserContext.get();
        log.info("用户分页查询已关注的会议：查询条件 = {}，用户id = {}", query, userId);
        return CommonResponseVO.success(conferenceService.queryFollowingConferencesByPageAndUserId(userId, query));
    }
}
