package demo.springboot.scheduler.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dean.lee
 */
@Component
public class TestScheduling {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    @Scheduled(fixedDelay = 3000)
    public void printDate(){
        System.out.println(sdf.format(new Date()));
    }
}
