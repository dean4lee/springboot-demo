package demo.springboot.validation.ctrl;

import demo.springboot.validation.domian.User;
import demo.springboot.validation.util.JsonResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author dean.lee
 */
@RestController
//在请求方法中校验需要在类上加该注解
@Validated
public class UserController {

    //测试post请求
    //@Validated用于校验参数，如果参数校验失败，错误信息封装到BindingResult
    @PostMapping("/login")
    public JsonResult login(@Validated @RequestBody User user, BindingResult bindingResult){
        //判断BindingResult中是否有错误信息
        if(bindingResult.hasErrors()){
            return JsonResult.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return JsonResult.success("登陆成功");
    }

    //测试get请求
    @GetMapping("/getLogin")
    public JsonResult getLogin(@Validated User user, BindingResult bindingResult){
        System.out.println(user);
        if(bindingResult.hasErrors()){
            return JsonResult.fail(bindingResult.getFieldError().getDefaultMessage());
        }
        return JsonResult.success("登陆成功");
    }

    //测试请求方法中校验
    //这种校验不支持封装到BindingResult中，需要自己做异常处理
    @GetMapping("/getUser")
    public JsonResult getUser(@NotNull(message = "用户名不能为空") String username){
        User user = new User();
        user.setUsername(username);
        user.setPassword("123456");
        return JsonResult.success(user);
    }
}
