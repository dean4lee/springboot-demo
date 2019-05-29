package demo.springboot.validation.ctrl;

import demo.springboot.validation.annotation.Phone;
import demo.springboot.validation.util.JsonResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author dean.lee
 */
@RestController
@Validated
public class PhoneController {

    @GetMapping("/sendPhone")
    public JsonResult sendPhone(@Phone @NotNull(message = "手机号不能为空") String phone){
        return JsonResult.success("正确的手机号");
    }
}
