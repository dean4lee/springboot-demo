package demo.springboot.mysql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MysqlApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void testJdbc() {
		List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from sys_user");
		maps.forEach(System.out::println);
	}

}
