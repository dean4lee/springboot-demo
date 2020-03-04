package demo.springboot.multipledatasource.dao2;

import demo.springboot.multipledatasource.domain.User;

import java.util.List;

public interface User2DAO {

    List<User> selectAll();
}
