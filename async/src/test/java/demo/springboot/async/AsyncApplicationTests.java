package demo.springboot.async;

import demo.springboot.async.service.AsyncService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncApplicationTests {

    @Autowired
    private AsyncService asyncService;

    @Test
    public void testAsync() {
        asyncService.test1();
        asyncService.test2();
        asyncService.test3();
        //避免主线程结束出现错误
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
