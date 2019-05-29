package demo.springboot.redissession.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dean.lee
 */
@RestController
public class TestController {

    //设置参数到session
    @GetMapping("/setValue")
    public String setValue(String value, HttpServletRequest request){
        System.out.println("setValue");
        request.getSession().setAttribute("value", value);
        return value;
    }

    //获取session中的参数
    @GetMapping("getValue")
    public String getValue(HttpServletRequest request){
        System.out.println("getValue");
        return (String) request.getSession().getAttribute("value");
    }
}
