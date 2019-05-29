package demo.springboot.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author dean.lee
 */
@Component
public class AsyncService {

    //@Async表示方法是一个异步方法
    @Async
    public void test1(){
        //打印线程name
        System.out.println("test1 start:" + Thread.currentThread().getName());
        //模拟程序执行
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test1 end");
    }

    @Async
    public void test2(){
        System.out.println("test2 start:" + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test2 end");
    }

    @Async
    public void test3(){
        System.out.println("test3 start:" + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test3 end");
    }
}
