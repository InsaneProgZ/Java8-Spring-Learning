package Learning.springboot.configuration;

import Learning.springboot.model.UserData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.HypermediaRestTemplateConfigurer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@EnableHypermediaSupport(type = {EnableHypermediaSupport.HypermediaType.HAL})
public class ConfigurationGeneral {

    @Bean
    RestTemplate restTemplate(HypermediaRestTemplateConfigurer configurer) {
        return configurer.registerHypermediaTypes(new RestTemplate());
    }

    @Bean
    public List<UserData> userList() {
        return new ArrayList<>();
    }
}