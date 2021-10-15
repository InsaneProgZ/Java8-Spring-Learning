package learning.springboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.Entity;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@EqualsAndHashCode(callSuper = false)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

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