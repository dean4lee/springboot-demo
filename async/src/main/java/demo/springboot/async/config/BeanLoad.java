package demo.springboot.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author dean.lee
 */
@Configuration
public class BeanLoad {

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("springboot-");
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(10);

        executor.initialize();
        return executor;
    }
}
