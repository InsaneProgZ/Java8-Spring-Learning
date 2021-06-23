package Learning.springboot.configuration;

import Learning.springboot.model.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        ObjectMapper objectMapper = builder.build();
        mappingInformation.configureObjectMapper(objectMapper);
        return objectMapper;
    }

    @Bean
    public List<UserData> userList() {
        return new ArrayList<>();
    }
}