package demo.springboot.websocket.handler;

import demo.springboot.websocket.principal.MyPrincipal;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * 连接时校验用户信息，并返回重写的Principal
 */
@Component
public class MyHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        if (!(request instanceof ServletServerHttpRequest)) {
            return null;
        }
        ServletServerHttpRequest req = (ServletServerHttpRequest) request;
        //获取请求参数中携带的token
        String uid = req.getServletRequest().getParameter("uid");
        if(uid == null){
            throw new RuntimeException("未登录");
        }
        return new MyPrincipal(uid);
    }
}
