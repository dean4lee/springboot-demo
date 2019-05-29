package demo.springboot.service;

import demo.springboot.domain.User;

/**
 * @author dean.lee
 */
public interface TestService {


    String getString(String src);

    User getUser(String name, int age);
}
