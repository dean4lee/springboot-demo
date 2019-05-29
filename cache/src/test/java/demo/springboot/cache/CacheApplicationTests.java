package demo.springboot.cache;

import demo.springboot.cache.service.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTests {

    @Autowired
    private CacheService cacheService;

    @Test
    public void testCache() {
        System.out.println(cacheService.getName(1L));
        System.out.println(cacheService.getName(1L));
        cacheService.updateName(1L);
        System.out.println(cacheService.getName(1L));
        cacheService.deleteName(1L);
        System.out.println(cacheService.getName(1L));
    }

}
