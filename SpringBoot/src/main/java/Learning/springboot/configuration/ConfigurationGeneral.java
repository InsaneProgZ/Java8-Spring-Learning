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
        var objectMapper = builder.build();
        mappingInformation.configureObjectMapper(objectMapper);
        return objectMapper;
    }

    @Bean
    public List<UserData> userList() {
        var a =new ArrayList<UserData>(1);
        a.add(new UserData("YanBean", 2, 3));
        return a;
    }
    @Bean(name = "segundo")
    public List<UserData> userList2(String name) {
        var a =new ArrayList<UserData>(1);
        a.add(new UserData(name, 4, 7));
        return a;
    }
}