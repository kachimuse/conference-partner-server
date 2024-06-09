package cn.edu.ecnu.conferencepartner.interceptor;

import cn.edu.ecnu.conferencepartner.common.context.UserContext;
import cn.edu.ecnu.conferencepartner.common.exception.BaseException;
import cn.edu.ecnu.conferencepartner.common.utils.JwtUtil;
import cn.edu.ecnu.conferencepartner.common.vo.CommonResult;
import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

/**
 * 拦截器，用于校验 JWT 令牌并设置线程上下文
 */
@Component
@RequiredArgsConstructor
public class JwtUserInterceptor implements HandlerInterceptor {

    @Value("${conference-partner.jwt.secret-key}")
    private String secretKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取 JWT 令牌
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new BaseException("The Authorization header is missing or invalid.");
        }
        String token = authorizationHeader.substring("Bearer ".length());
        //解析 JWT 令牌
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(secretKey, token);
        } catch (JwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new BaseException("The JWT token is invalid.");
        }
        //设置线程上下文
        UserContext.set(Long.valueOf(claims.get("userId").toString()));

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
