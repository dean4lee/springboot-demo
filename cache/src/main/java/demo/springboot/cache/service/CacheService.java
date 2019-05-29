package demo.springboot.cache.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author dean.lee
 */
@Service
public class CacheService {

    /**
     * 如果缓存中有则直接走缓存，如果没有则执行方法，将方法的返回值作为缓存
     * @param id
     * @return
     */
    @Cacheable(value = "cache-test", key = "'getName:' + #p0")
    public String getName(long id){
        System.out.println("等待3秒。。。。");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return id + ":name";
    }

    /**
     * 将方法的返回值更新到缓存
     * @param id
     * @return
     */
    @CachePut(value = "cache-test", key = "'getName:' + #p0")
    public String updateName(long id){
        System.out.println("更新名称");
        return id + ":nickname";
    }

    /**
     * 删除缓存
     * @param id
     */
    @CacheEvict(value = "cache-test", key = "'getName:' + #p0")
    public void deleteName(long id){
        System.out.println("删除名称");
    }
}
