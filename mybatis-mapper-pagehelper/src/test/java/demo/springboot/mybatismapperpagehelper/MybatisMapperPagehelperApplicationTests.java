package demo.springboot.mybatismapperpagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.springboot.mybatismapperpagehelper.dao.SysUserDAO;
import demo.springboot.mybatismapperpagehelper.domain.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisMapperPagehelperApplicationTests {

    @Autowired
    private SysUserDAO userDAO;

    @Test
    public void testSelectByPrimaryKey() {
        //根据主键查询
        SysUser sysUser = userDAO.selectByPrimaryKey(1L);
        System.out.println(sysUser);
    }

    @Test
    public void testSelectAll() {
        //查询所有数据
        List<SysUser> sysUsers = userDAO.selectAll();
        System.out.println(sysUsers);
    }

    @Test
    public void testPageHelper(){
        //第一个参数为页数，第二个为条数。对这个方法后的第一个sql生效，
        //单表分页，一对一分页正确。一对多，多对多分页会出现错误
        //PageHelper.startPage(1, 10);
        //根据id倒序排序
        PageHelper.startPage(1, 2, "id desc");
        List<SysUser> sysUsers = userDAO.selectAll();

        //获取分页数据
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);
        //获取总条数
        System.out.println(pageInfo.getTotal());
        //获取分页页数
        System.out.println(pageInfo.getPageNum());
        //获取分页条数
        System.out.println(pageInfo.getPageSize());
        //获取分页数据，即上面查询到的sysUsers
        System.out.println(pageInfo.getList());
    }

}
