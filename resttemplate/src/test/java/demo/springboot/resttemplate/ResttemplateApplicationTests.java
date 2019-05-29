package demo.springboot.resttemplate;

import demo.springboot.resttemplate.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResttemplateApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testGetRequest(){
        //直接获取响内容
        String object = restTemplate.getForObject("http://localhost:8080/getString?src=hello", String.class);
        System.out.println(object);

        //获取相应信息，包含响应状态、响应头、响应内容
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8080/getString?src=hello", String.class);
        System.out.println(entity);
        //响应状态码
        System.out.println(entity.getStatusCode());
        //响应头
        System.out.println(entity.getHeaders());
        //响应内容
        System.out.println(entity.getBody());
    }

    @Test
    public void testPostRequest(){
        Map<String, Object> postData = new HashMap<>();
        postData.put("id", 1L);
        postData.put("name", "测试");
        postData.put("age", 18);
        User user = restTemplate.postForObject("http://localhost:8080/getUser", postData, User.class);
        System.out.println(user);
    }

    @Test
    public void testPostHeaders(){
        //设置请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json;charset=utf-8");

        //设置请求参数
        Map<String, Object> postData = new HashMap<>();
        postData.put("id", 1L);
        postData.put("name", "测试");
        postData.put("age", 18);

        //将请求头和请求参数设置到HttpEntity中
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(postData, httpHeaders);

        User user = restTemplate.postForObject("http://localhost:8080/getUser", httpEntity, User.class);
        System.out.println(user);
    }

}
