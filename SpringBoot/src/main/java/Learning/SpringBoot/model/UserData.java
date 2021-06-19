package Learning.SpringBoot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Objects;


public class UserData extends RepresentationModel {

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
    private Integer rg;

    public UserData(String name, Integer age, Integer rg) {
        this.name = name;
        this.age = age;
        this.rg = rg;
    }

    public UserData(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserData userData = (UserData) o;
        return Objects.equals(name, userData.name) && Objects.equals(age, userData.age) && Objects.equals(rg, userData.rg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, age, rg);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRg() {
        return rg;
    }

    public void setRg(Integer rg) {
        this.rg = rg;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
