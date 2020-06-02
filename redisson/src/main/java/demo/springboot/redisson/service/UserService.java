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
        // 获取锁，以username为锁名称。这样用户名相同的数据添加时，无法并行处理。
        // 第二次的请求需要等到第一次请求的锁释放后才可以继续执行
        RLock lock = redissonClient.getLock(user.getUsername());
        // 默认锁30秒，如果当前线程处理时间过长。
        // redisson会在锁时间过了三分之二的时候将锁的时间重新设置为30秒
        lock.lock();
        //将需要锁住的代码try起来，并在finally中释放锁
        try {
            // 判断用户账号是否重复
            SysUser sysUser = userMapper.selectByUsername(user.getUsername());
            // 如果用户存在，抛出异常
            if (sysUser != null) {
                throw new RuntimeException("用户已经存在");
            }
            // 模拟线程不安全的情况
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 不重复则添加用户
            userMapper.insert(user);
        }finally {
            // 释放锁
            lock.unlock();
        }
        return "success";
    }
}
