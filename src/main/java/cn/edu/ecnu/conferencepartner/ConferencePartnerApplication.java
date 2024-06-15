package cn.edu.ecnu.conferencepartner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class ConferencePartnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferencePartnerApplication.class, args);
    }

}
