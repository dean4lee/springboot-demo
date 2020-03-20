package demo.springboot.shardingjdbc.dao;

import demo.springboot.shardingjdbc.domain.Dict;
import org.apache.ibatis.annotations.Insert;

public interface DictDAO {

    @Insert("insert into t_dict(name) values(#{name})")
    int insert(Dict dict);
}
