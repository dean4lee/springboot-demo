package demo.springboot.limiter.ctrl;

import demo.springboot.limiter.annotation.Limiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dean.lee
 */
@RestController
public class TestController {

    //限制在周期内只能访问3次
    @Limiter(frequency = 3)
    @GetMapping("getString")
    public String getString(){
        return "hello";
    }
}
