package demo.springboot.validation.domian;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author dean.lee
 */
@Data
public class User implements Serializable {

    @Length(min = 5, max = 10, message = "用户名长度不合法")
    @NotNull(message = "用户名不能为空")
    private String username;

    @Length(min = 6, max = 16, message = "密码长度不合法")
    @NotNull(message = "密码不能为空")
    private String password;
}
