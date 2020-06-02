package demo.springboot.redisson.controller;

import demo.springboot.redisson.domain.SysUser;
import demo.springboot.redisson.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("addUser")
    public String addUser(@RequestBody SysUser user){
        return userService.addUser(user);
    }
}
