package cn.edu.ecnu.conferencepartner.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Knife4j配置
 */
@Configuration
public class Knife4jConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("会伴API")
                .description("仿会伴项目接口文档")
                .version("v1.0")
                .contact(new Contact()
                        .name("龚奕玮")
                        .email("51265902114@stu.ecnu.edu.cn"))
        );
    }

}
