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
    @JsonProperty("saldo")
    private BigDecimal balance;

    public UserData(String name, Integer age, BigDecimal balance) {
        this.name = name;
        this.age = age;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}