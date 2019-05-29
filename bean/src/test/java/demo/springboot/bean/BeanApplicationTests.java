package demo.springboot.bean;

import demo.springboot.bean.bean.Test1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanApplicationTests {

    @Autowired
    private demo.springboot.bean.bean.Test test;
    @Autowired
    private Test1 test1;

    @Test
    public void testBean() {
        test.setName("test");
        System.out.println(test);
        System.out.println(test1);
    }

}
