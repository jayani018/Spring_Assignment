package lk.ijse.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author : Jayani_Arunika  10/11/2023 - 12:53 AM
 * @since : v0.01.0
 **/
@Configuration
@Import({JPAConfig.class})
@ComponentScan(basePackages = "lk.ijse.service")
public class WebRootConfig {
    public WebRootConfig(){
        System.out.println("WebRootConfig : Instantiated");
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
