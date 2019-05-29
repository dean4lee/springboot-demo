package demo.springboot.validation.ctrl;

import demo.springboot.validation.domian.Role;
import demo.springboot.validation.group.Add;
import demo.springboot.validation.group.Update;
import demo.springboot.validation.util.JsonResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dean.lee
 */
@RestController
public class RoleController {

    @GetMapping("add")
    public JsonResult add(@Validated(Add.class) Role role){
        return JsonResult.success("添加成功");
    }

    @GetMapping("update")
    public JsonResult update(@Validated(Update.class) Role role){
        return JsonResult.success("修改成功");
    }
}
