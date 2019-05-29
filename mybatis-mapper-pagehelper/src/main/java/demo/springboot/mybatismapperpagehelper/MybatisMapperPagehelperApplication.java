package demo.springboot.mybatismapperpagehelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//这里使用的扫描注解不是mybatis的，是通用mapper的注解
@MapperScan("demo.springboot.mybatismapperpagehelper.dao")
public class MybatisMapperPagehelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisMapperPagehelperApplication.class, args);
    }

}
