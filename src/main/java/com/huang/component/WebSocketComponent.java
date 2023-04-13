package com.huang.component;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/ws/{username}/{role}/{token}")  // 接口路径 ws://ip:port/ws/{username}/{role}/{token}
public class WebSocketComponent {
    //用户名
    private String username;

    //用户权限
    private String role;

    //token
    private String token;

    //websocket连接
    private Session session;

    //连接池
    private static CopyOnWriteArrayList<WebSocketComponent> sessionPool = new CopyOnWriteArrayList<WebSocketComponent>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username, @PathParam("role") String role, @PathParam("token") String token) {
        this.session = session;
        this.username = username;
        this.role = role;
        this.token = token;
        sessionPool.add(this);
        System.out.println(this.username + "/" + this.role + " connected!");
    }
    
    @OnClose
    public void onClose() {
        sessionPool.remove(this);
        System.out.println(this.username + "/" + this.role + " disconnected!");
    }

    @OnMessage
    public void onMessage(String message) {
    	System.out.println(this.username + "/" + this.role + " recieved: " + message);
    }

    @OnError
    public void onError(Throwable error) {
        System.out.println(this.username + "/" + this.role + " errored: " + error.toString());

    }

    //发送消息
    private void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        }
        catch (Exception e) {
            System.out.println("向" + this.username + "/" + this.role + " sent message error：" + e.toString());
        }
    }

    //通过token发送消息
    public static void sendMessageToUserByToken(String token, String message) {
        sessionPool.forEach(component -> {
            if (component.token.equals(token)) {
                component.sendMessage(message);
            }
        });
    }

    //通过用户名发送消息
    public static void sendMessageToUserByUsername(String username, String message) {
        sessionPool.forEach(component -> {
            if (component.username.equals(username)) {
                component.sendMessage(message);
            }
        });
    }

    //通过权限发送消息
    public static void sendMessageToUserByRole(String role, String message) {
        sessionPool.forEach(component -> {
            if (component.role.equals(role)) {
                component.sendMessage(message);
            }
        });
    }

    //向所有用户发送消息
    public static void sendMessageToAllUsers(String message) {
        sessionPool.forEach(component -> {
            component.sendMessage(message);
        });
    }
}
