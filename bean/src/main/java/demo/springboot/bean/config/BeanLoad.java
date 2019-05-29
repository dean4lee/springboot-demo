package demo.springboot.bean.config;

import demo.springboot.bean.bean.Test;
import demo.springboot.bean.bean.Test1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration注解声明当前类是一个配置类，相当于spring中xml的<beans>
@Configuration
public class BeanLoad {

    //@Bean注解相当于spring中xml的<bean>
    //当前方法返回的值会被注册成bean
    //bean默认的名称是方法名
    //如果需要设置自定义名称修改@Bean中name属性
    @Bean(name = "t")
    public Test test(){
        return new Test();
    }

    //需要依赖其他bean，在方法参数中加入即可
    @Bean
    public Test1 test1(Test test){
        Test1 test1 = new Test1();
        test1.setTest(test);
        return test1;
    }

    //或者在当前类使用@Autowired注解装配bean，方法参数就可以为空
//    @Autowired
//    private Test test;
//    @Bean
//    public Test1 test1(){
//        Test1 test1 = new Test1();
//        test1.setTest(test);
//        return test1;
//    }
}
