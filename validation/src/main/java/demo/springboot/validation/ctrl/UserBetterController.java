package demo.springboot.validation.ctrl;

import demo.springboot.validation.domian.User;
import demo.springboot.validation.util.JsonResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author dean.lee
 */
@RestController
@RequestMapping("/better")
@Validated
public class UserBetterController {

    @PostMapping("/login")
    public JsonResult login(@Validated @RequestBody User user){
        return JsonResult.success("登陆成功");
    }

    @GetMapping("/getLogin")
    public JsonResult getLogin(@Validated User user){
        return JsonResult.success("登陆成功");
    }

    @GetMapping("/getUser")
    public JsonResult getUser(@NotNull(message = "用户名不能为空") String username){
        User user = new User();
        user.setUsername(username);
        user.setPassword("123456");
        return JsonResult.success(user);
    }
}
