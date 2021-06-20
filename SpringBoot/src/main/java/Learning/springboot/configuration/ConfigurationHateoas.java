package Learning.springboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.mediatype.hal.CurieProvider;
import org.springframework.hateoas.mediatype.hal.DefaultCurieProvider;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableHypermediaSupport(type = {EnableHypermediaSupport.HypermediaType.HAL})
public class ConfigurationHateoas {

//    @Bean
//    public CurieProvider curieProvider() {
//        return new DefaultCurieProvider("ex", UriTemplate.of("https://www.example.com/rels/{rel}"));
//    }
}