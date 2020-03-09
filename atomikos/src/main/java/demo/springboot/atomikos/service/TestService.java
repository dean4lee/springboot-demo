package demo.springboot.atomikos.service;

import demo.springboot.atomikos.dao1.UserDAO1;
import demo.springboot.atomikos.dao2.UserDAO2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {
    @Autowired
    private UserDAO1 userDAO1;
    @Autowired
    private UserDAO2 userDAO2;

    /**
     * 在需要事务的方法加上@Transactional注解即可
     */
    @Transactional
    public void test(){
        userDAO1.updateById("haha", 1L);
        userDAO2.updateById("hehe", 2L);
        //模拟异常
        int a = 1/0;
    }
}
