package demo.springboot.ctrl;

import com.alibaba.dubbo.config.annotation.Reference;
import demo.springboot.domain.User;
import demo.springboot.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dean.lee
 */
@RestController
public class TestController {

    @Reference(version = "${application.version}")
    private TestService testService;

    @GetMapping("/getString")
    public String getString(String src){
        return testService.getString(src);
    }

    @GetMapping("/getUser")
    public User getUser(String name, Integer age){
        return testService.getUser(name, age);
    }
}
