package demo.springboot.properties.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesBean {
    @Value("${bean.name}")
    private String name;

    @Value("${bean.age}")
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
        return "PropertiesBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
