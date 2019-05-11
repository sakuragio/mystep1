package com.wteng.websocket.practise;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by wangteng on 2019/5/10.
 */
@ServerEndpoint("/echo")
public class SimpleSocket1 {

    private String START_TIME = "start_time";
    private Session session;

    @OnOpen
    public void init(Session session) {
        this.session = session;
        session.getUserProperties().put(START_TIME, System.currentTimeMillis());
    }

    @OnMessage
    public void getMessage(String message) {
        if(message.indexOf("***") != -1) {
            throw new RuntimeException("不允许***");
        } else if(message.indexOf("close") != -1) {
            try {
                Thread.sleep(1000);
                sendMessage("关闭连接：连接持续" + (System.currentTimeMillis() - (Long) session.getUserProperties().get(START_TIME))/1000 + "s");
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        sendMessage("我收到【" + message + "】");
    }

    @OnError
    public void whenError(Throwable t) {
        sendMessage("产生错误：" + t.getMessage());
    }

    @OnClose
    public void whenClose() {
        System.out.println("关闭");
    }

    public void sendMessage(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
