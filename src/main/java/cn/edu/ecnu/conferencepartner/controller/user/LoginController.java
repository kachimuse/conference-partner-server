package cn.edu.ecnu.conferencepartner.controller.user;

import cn.edu.ecnu.conferencepartner.common.dto.UserLoginDTO;
import cn.edu.ecnu.conferencepartner.common.dto.UserRegisterDTO;
import cn.edu.ecnu.conferencepartner.common.utils.JwtUtil;
import cn.edu.ecnu.conferencepartner.common.vo.CommonResult;
import cn.edu.ecnu.conferencepartner.common.vo.UserVO;
import cn.edu.ecnu.conferencepartner.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 龚奕玮
 * @since 2024-06-06
 */
@Tag(name = "用户端 - 登录管理")
@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final IUserService userService;

    @Value("${conference-partner.jwt.secret-key}")
    private String secretKey;
    @Value("${conference-partner.jwt.time-to-live}")
    private Long timeToLive;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public CommonResult<?> login(@RequestBody UserLoginDTO userLoginDTO) throws Exception {
        log.info("用户登录：{}", userLoginDTO);
        UserVO user = userService.login(userLoginDTO);

        //登录成功，生成并返回jwt令牌
        Map<String, Object> claims = new HashMap<>() {{
            put("userId", user.getId());
            put("email", user.getEmail());
            put("admin", user.getIsAdmin());
        }};
        String token = JwtUtil.createJWT(secretKey, timeToLive, claims);

        log.info("Genearate JWT token for user " + user.getEmail() + " (id = " + user.getId() + "): " + token);
        return CommonResult.success(token);
    }

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public CommonResult<?> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("用户注册：{}", userRegisterDTO);
        userService.register(userRegisterDTO);
        return CommonResult.success();
    }
}
