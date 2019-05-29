package demo.springboot.mybatis;

import demo.springboot.mybatis.dao.SysUserMapper;
import demo.springboot.mybatis.domain.SysUser;
import demo.springboot.mybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void testMybatis() {
        SysUser sysUser = userMapper.selectById(1L);
        System.out.println(sysUser);
    }

    @Test
    public void testTransactional(){
        System.out.println(userMapper.selectById(1L));

        try {
            userService.updateUserAge();
        }catch (RuntimeException e){

        }

        System.out.println(userMapper.selectById(1L));
    }

}
