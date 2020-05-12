package demo.springboot.websocket.config;

import demo.springboot.websocket.handler.MyHandshakeHandler;
import demo.springboot.websocket.handler.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
//开启消息代理，默认使用内置消息代理，也可以选择配置RabbitMQ等
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Autowired
    private MyWebSocketHandler myWebSocketHandler;
    @Autowired
    private MyHandshakeHandler myHandshakeHandler;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //启用/user /topic两个消息前缀,消息发送的前缀，也是前端订阅的前缀
        registry.enableSimpleBroker("/user", "/topic");
        //当使用convertAndSendToUser发送消息时，前端订阅用/user开头。即一对一发送消息，使用/user为前缀订阅
        registry.setUserDestinationPrefix("/user");
        //前端向服务端发送消息的前缀
        registry.setApplicationDestinationPrefixes("/im/");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //客户端和服务端进行连接的endpoint
        //如果使用移动端开发app，需要/im/conn/websocket连接
        stompEndpointRegistry.addEndpoint("/im/conn")
                .setHandshakeHandler(myHandshakeHandler)//设置连接校验
                .setAllowedOrigins("*")//跨域
                .withSockJS();//开启sockjs
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        //注册登陆退出
        registry.addDecoratorFactory(myWebSocketHandler);
    }
}
