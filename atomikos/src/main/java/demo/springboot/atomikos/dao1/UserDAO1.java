package demo.springboot.atomikos.dao1;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface UserDAO1 {

    @Update("update user set name = #{name} where id = #{id}")
    int updateById(@Param("name")String name, @Param("id")Long id);
}
