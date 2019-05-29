package demo.springboot.mybatismapperpagehelper.domain;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author dean.lee
 */
@Data
public class SysUser {
    //根据主键查询时必须要有@Id注解，否则会报错
    @Id
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private Integer age;
}
