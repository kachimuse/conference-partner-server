package cn.edu.ecnu.conferencepartner.config;

import cn.edu.ecnu.conferencepartner.interceptor.JwtAdminInterceptor;
import cn.edu.ecnu.conferencepartner.interceptor.JwtUserInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC 配置
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final JwtAdminInterceptor jwtAdminInterceptor;
    private final JwtUserInterceptor jwtUserInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtAdminInterceptor)
                .addPathPatterns("/admin/**");
        registry.addInterceptor(jwtUserInterceptor)
                .addPathPatterns("/account/**")
                .addPathPatterns("/conferences/*/follow");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
