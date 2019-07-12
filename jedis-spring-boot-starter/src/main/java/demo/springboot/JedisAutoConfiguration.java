package demo.springboot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * @author dean.lee
 */
@Configuration
//当类路径下有Jedis依赖时进行配置
@ConditionalOnClass(Jedis.class)
//引入JedisProperties对象
@EnableConfigurationProperties(JedisProperties.class)
public class JedisAutoConfiguration {

    @Bean
    //当spring容器中没有该Bean时注册
    @ConditionalOnMissingBean(Jedis.class)
    public Jedis jedis(JedisProperties jedisProperties){
        //设置地址端口
        Jedis jedis = new Jedis(jedisProperties.getHost(), jedisProperties.getPort());
        //验证密码
        jedis.auth(jedisProperties.getPassword());
        //设置索引库
        jedis.select(jedisProperties.getDatabase());
        jedis.connect();
        return jedis;
    }

}
