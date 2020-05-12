package demo.springboot.websocket.controller;

import demo.springboot.websocket.message.SendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ImController {
    //发送消息的模板
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

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
        //发送给订阅/user/{toUid}/msg的用户
        //这里的toUid是接收消息用户的uid
        simpMessagingTemplate.convertAndSendToUser(msg.getToUid(), "msg", msg);
        return "success";
    }

    /**
     * 发送消息，发送给所有订阅/topic/sys的用户
     * 也可以使用@SendTo注解，返回值为发送的消息即可
     *
     * @param msg
     * @return
     */
    @GetMapping("/sendAll")
//    @SendTo("/topic/sys")
    public String sendAll(String msg){
        System.out.println("广播消息：" + msg);
        //如果是群聊，根据传递参数的群聊房间号，动态拼接/topic/{房间号}，前端订阅/topic/{房间号}即可
        simpMessagingTemplate.convertAndSend("/topic/sys", msg);
        return "success";
//        return msg;
    }

}