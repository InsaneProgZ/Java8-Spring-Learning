package yanzingra.learning.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
public class UserData {

    @NotNull
    @Size(min = 1, max = 20)
    @JsonProperty("nome")
    private String name;

    @NotNull
    @Positive
    @JsonProperty("idade")
    private Integer age;

    @NotNull
    @Positive
    @JsonProperty("rg")
    private BigDecimal rg;

    public UserData(String name, Integer age, BigDecimal rg) {
        this.name = name;
        this.age = age;
        this.rg = rg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRg() {
        return rg;
    }

    public void setBalance(BigDecimal rg) {
        this.rg = rg;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}