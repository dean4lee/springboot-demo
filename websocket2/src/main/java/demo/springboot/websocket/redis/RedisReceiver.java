package demo.springboot.websocket.redis;

import com.alibaba.fastjson.JSONObject;
import demo.springboot.websocket.message.SendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * 处理订阅的消息
 */
@Component
public class RedisReceiver {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 处理一对一消息
     * @param message
     */
    public void sendMsg(String message) {
        SendMsg msg = JSONObject.parseObject(message, SendMsg.class);
        simpMessagingTemplate.convertAndSendToUser(msg.getToUid(), "msg", msg);
    }

    /**
     * 处理广播消息
     * @param message
     */
    public void sendAllMsg(String message){
        simpMessagingTemplate.convertAndSend("/topic/sys", message);
    }
}