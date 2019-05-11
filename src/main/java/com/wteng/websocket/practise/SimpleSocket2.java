package com.wteng.websocket.practise;

import javax.websocket.*;
import java.io.IOException;

/**
 * Created by wangteng on 2019/5/11.
 */
public class SimpleSocket2 extends Endpoint {

    private String START_TIME = "start_time";
    private Session mySession;

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        mySession = session;
        mySession.getUserProperties().put(START_TIME, System.currentTimeMillis());
        mySession.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String message) {
                if(message.contains("***")) {
                    throw new RuntimeException("不允许***");
                } else if(message.contains("close")) {
                    try {
                        Thread.sleep(1000);
                        sendMessage("关闭连接：连接持续" + (System.currentTimeMillis() - (Long) mySession.getUserProperties().get(START_TIME))/1000 + "s");
                        mySession.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return;
                }

                sendMessage("我收到【" + message + "】");
            }
        });
    }

    @Override
    public void onError(Session session, Throwable thr) {
        sendMessage("产生错误：" + thr.getMessage());
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("关闭");
        super.onClose(session, closeReason);
    }

    public void sendMessage(String message) {
        try {
            mySession.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
