package demo.springboot.properties.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bean")
//@Component
public class PropertiesBean1 {

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PropertiesBean1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
