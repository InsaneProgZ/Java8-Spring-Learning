package learning.springboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Component
@EqualsAndHashCode(callSuper = false)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User extends RepresentationModel<User> {

    @Size(min = 1, max = 20)
    @JsonProperty("nome")
    private String name;

    @Positive
    @JsonProperty("idade")
    private Integer age;


    @Positive
    @JsonProperty("rg")
    private Integer rg;

    private Integer cost;
}