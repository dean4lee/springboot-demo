package demo.springboot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import demo.springboot.domain.User;
import demo.springboot.service.TestService;

/**
 * @author dean.lee
 */
@Service(version = "${application.version}")
public class TestServiceImpl implements TestService {
    @Override
    public String getString(String src) {
        return src;
    }

    @Override
    public User getUser(String name, int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }
}
