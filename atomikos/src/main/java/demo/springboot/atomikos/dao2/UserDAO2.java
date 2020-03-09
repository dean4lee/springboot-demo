package demo.springboot.atomikos.dao2;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface UserDAO2 {

    @Update("update user set name = #{name} where id = #{id}")
    int updateById(@Param("name")String name, @Param("id")Long id);
}
