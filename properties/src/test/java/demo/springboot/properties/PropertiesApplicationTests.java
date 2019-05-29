package demo.springboot.properties;

import demo.springboot.properties.bean.PropertiesBean;
import demo.springboot.properties.bean.PropertiesBean1;
import demo.springboot.properties.bean.PropertiesBean2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties({PropertiesBean1.class})
public class PropertiesApplicationTests {

    @Autowired
    private PropertiesBean bean;
    @Autowired
    private PropertiesBean1 bean1;
    @Autowired
    private PropertiesBean2 bean2;
    @Autowired
    private Environment env;

    @Test
    public void loadBean() {
        System.out.println(bean);
    }

    @Test
    public void loadBean1() {
        System.out.println(bean1);
    }

    @Test
    public void loadBean2() {
        System.out.println(bean2);
    }

    @Test
    public void testEnvironment() {
        System.out.println(env.getProperty("bean.name"));
    }
}
