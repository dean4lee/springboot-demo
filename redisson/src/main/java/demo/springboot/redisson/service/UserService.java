package demo.springboot.redisson.service;

import demo.springboot.redisson.dao.SysUserMapper;
import demo.springboot.redisson.domain.SysUser;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private RedissonClient redissonClient;

    public String addUser(SysUser user){
        RLock lock = redissonClient.getLock(user.getUsername());
        lock.lock();
        try {
            //判断用户账号是否重复
            SysUser sysUser = userMapper.selectByUsername(user.getUsername());
            //如果用户存在，抛出异常
            if (sysUser != null) {
                throw new RuntimeException("用户已经存在");
            }
            //模拟线程不安全的情况
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //不重复则添加用户
            userMapper.insert(user);
        }finally {
            lock.unlock();
        }
        return "success";
    }
}
