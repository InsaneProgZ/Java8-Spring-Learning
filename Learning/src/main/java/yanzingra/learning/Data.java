package yanzingra.learning;

import java.math.BigDecimal;

public class Data {
    String name;
    String function;
    Integer age;
    BigDecimal balance;

    public Data(String name, String function, Integer age, BigDecimal balance) {
        this.name = name;
        this.function = function;
        this.age = age;
        this.balance = balance;

    }

    public String getName() {
        return name;
    }

    public String getFunction() {
        return function;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
