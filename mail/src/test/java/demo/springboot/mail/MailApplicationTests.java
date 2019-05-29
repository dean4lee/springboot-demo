package demo.springboot.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailApplicationTests {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Test
    public void testMail() {
        //建立邮件消息
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //发送者
        mailMessage.setFrom(username);
        //接收者
        mailMessage.setTo("dean.lee@aliyun.com");
        //发送的标题
        mailMessage.setSubject("主题");
        //发送的内容
        mailMessage.setText("内容");
        //发送邮件
        mailSender.send(mailMessage);
    }

}
