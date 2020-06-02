package demo.springboot.redisson.dao;


import demo.springboot.redisson.domain.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface SysUserMapper {

    @Select("select * from sys_user where username = #{username}")
    SysUser selectByUsername(String username);

    @Insert("insert into sys_user(username, password) values(#{username}, #{password})")
    int insert(SysUser user);
}
