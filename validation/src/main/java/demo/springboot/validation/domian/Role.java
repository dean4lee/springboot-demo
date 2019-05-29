package demo.springboot.validation.domian;

import demo.springboot.validation.group.Add;
import demo.springboot.validation.group.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author dean.lee
 */
@Data
public class Role {

    //修改角色时，必须要有id
    @NotNull(message = "修改角色必须有id", groups = Update.class)
    private Long id;

    //添加角色时必须要有name
    @NotNull(message = "添加角色必须有name", groups = Add.class)
    //添加修改都需要name的长度在1-10
    @Length(min = 1, max = 10, message = "名称不合法", groups = {Add.class, Update.class})
    private String name;
}
