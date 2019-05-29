package demo.springboot.resttemplate.ctrl;

import demo.springboot.resttemplate.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dean.lee
 */
@RestController
public class TestController {

    @GetMapping("getString")
    public String getString(String src){
        return src;
    }

    @PostMapping("getUser")
    public User getUser(@RequestBody User user){
        return user;
    }
}
