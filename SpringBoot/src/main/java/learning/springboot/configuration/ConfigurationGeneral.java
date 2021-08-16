package learning.springboot.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import learning.springboot.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.HypermediaMappingInformation;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConfigurationGeneral {

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder,
                                     HypermediaMappingInformation mappingInformation) {
        var objectMapper = builder.build();
        mappingInformation.configureObjectMapper(objectMapper);
        return objectMapper;
    }

    @Bean(name =  "first")
    public List<User> userList() {
        var a =new ArrayList<User>(1);
        a.add(new User("YanBean", 2, 3, 2));
        return a;
    }
}