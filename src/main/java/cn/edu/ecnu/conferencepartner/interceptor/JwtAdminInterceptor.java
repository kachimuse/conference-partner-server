package cn.edu.ecnu.conferencepartner.interceptor;

import cn.edu.ecnu.conferencepartner.common.context.UserContext;
import cn.edu.ecnu.conferencepartner.common.exception.BusinessException;
import cn.edu.ecnu.conferencepartner.common.exception.ForbiddenException;
import cn.edu.ecnu.conferencepartner.common.exception.UnauthorizedException;
import cn.edu.ecnu.conferencepartner.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截器，用于校验 JWT 令牌并设置线程上下文
 */
@Component
@RequiredArgsConstructor
public class JwtAdminInterceptor implements HandlerInterceptor {

    @Value("${conference-partner.jwt.secret-key}")
    private String secretKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取 JWT 令牌
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("缺失或非法的Authorization请求头");
        }
        String token = authorizationHeader.substring("Bearer ".length());
        //解析 JWT 令牌
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(secretKey, token);
        } catch (JwtException e) {
            throw new UnauthorizedException("非法的token字段");
        }
        //检查管理员权限
        boolean isAdmin = (boolean) claims.get("admin");
        if (!isAdmin) {
            throw new ForbiddenException("用户无管理员权限");
        }
        //设置线程上下文
        UserContext.set(Long.valueOf(claims.get("userId").toString()));

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
