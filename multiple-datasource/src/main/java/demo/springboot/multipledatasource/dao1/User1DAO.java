package demo.springboot.multipledatasource.dao1;

import demo.springboot.multipledatasource.domain.User;

import java.util.List;

public interface User1DAO {

    List<User> selectAll();
}
