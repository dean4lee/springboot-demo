package demo.springboot.mybatis.service;

import demo.springboot.mybatis.dao.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dean.lee
 */
@Service
public class UserService {
    @Autowired
    private SysUserMapper userMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    public void updateUserAge(){
        userMapper.updateAgeById(40, 1L);
        int a = 1/0;
    }
}
