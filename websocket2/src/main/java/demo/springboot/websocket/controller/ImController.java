package demo.springboot.websocket.controller;

import com.alibaba.fastjson.JSONObject;
import demo.springboot.websocket.message.SendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

@RestController
public class ImController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 发送消息,一对一
     * Principal为连接websocket校验时返回的，可以直接在参数中使用
     *
     * @param msg
     * @param principal
     * @return
     */
    @MessageMapping("/send2user")
    public String send2user(@Validated SendMsg msg, Principal principal) {
        String uid = principal.getName();
        //当前发送信息的uid
        msg.setUid(uid);
        System.out.println(uid + ":" + msg);
        //获取在线的用户列表
        Set<String> onlineUsers = stringRedisTemplate.opsForSet().members("online");
        //判断发送的用户是否在线
        if (onlineUsers.contains(msg.getToUid())) {
            //如果用户在线，则将消息发送到redis im-topic主题消息中，所有连接同一个redis的应用并订阅im-topic主题都会收到这条消息。
            //然后都使用SimpMessagingTemplate发送消息到指定的订阅中
            //接收消息发送消息的类 RedisReceiver
            stringRedisTemplate.convertAndSend("im-topic", JSONObject.toJSONString(msg));
        } else {
           //用户不在线，保存消息记录，用户上线后拉取，这里不做实现
        }
        return "success";
    }

    /**
     * 发送消息，发送给所有订阅/topic/sys的用户
     * 广播消息只发送给在线的用户
     * @param msg
     * @return
     */
    @GetMapping("/sendAll")
    public String sendAll(String msg){
        System.out.println("广播消息：" + msg);
        //将消息发布到redis sys-topic主题中
        stringRedisTemplate.convertAndSend("sys-topic", msg);
        return "success";
    }

}