package Learning.springboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Repository
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@Data
public class UserData extends RepresentationModel<UserData> {

    @NotNull
    @Size(min = 1, max = 20)
    @JsonProperty("nome")
    private @Setter String name;

    @NotNull
    @Positive
    @JsonProperty("idade")
    private @Setter Integer age;


    @NotNull
    @Positive
    @JsonProperty("rg")
    private @Setter Integer rg;
}