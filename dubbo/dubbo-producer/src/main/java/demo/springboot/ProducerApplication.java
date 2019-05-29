package demo.springboot;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author dean.lee
 */
@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ProducerApplication.class)
                .web(WebApplicationType.NONE)   //非web应用
                .run(args);
    }
}
