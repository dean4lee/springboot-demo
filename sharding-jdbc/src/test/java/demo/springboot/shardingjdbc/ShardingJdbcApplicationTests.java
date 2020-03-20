package demo.springboot.shardingjdbc;

import demo.springboot.shardingjdbc.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbcApplicationTests {
    @Autowired
    private TestService testService;

    @Test
    public void testAddOrder() {
        testService.addOrder();
    }

    @Test
    public void testSelectAll(){
        List<Map> select = testService.selectAll();
        System.out.println(select);
    }

    @Test
    public void testAddDict(){
        testService.addDict();
    }
}
