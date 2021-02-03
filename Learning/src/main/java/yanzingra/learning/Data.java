package yanzingra.learning;

public class Data {
    String name;
    String function;
    Integer age;

    public Data(String name, String function, Integer age) {
        this.name = name;
        this.function = function;
        this.age = age;
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
