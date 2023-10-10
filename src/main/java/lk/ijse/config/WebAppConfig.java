package lk.ijse.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author : Jayani_Arunika  10/11/2023 - 12:53 AM
 * @since : v0.01.0
 **/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"lk.ijse.controller","lk.ijse.advisor"})
public class WebAppConfig {
    public WebAppConfig(){
        System.out.println("WebAppConfig:Web App Instantiated");
    }
}
