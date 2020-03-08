package demo.springboot.multipledatasource;

import demo.springboot.multipledatasource.dao1.User1DAO;
import demo.springboot.multipledatasource.dao2.User2DAO;
import demo.springboot.multipledatasource.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultipleDatasourceApplicationTests {

    @Autowired
    private User1DAO user1DAO;

    @Autowired
    private User2DAO user2DAO;

    @Test
    public void test() {
        List<User> users1 = user1DAO.selectAll();
        System.out.println(users1);
        List<User> users2 = user2DAO.selectAll();
        System.out.println(users2);
    }
}
