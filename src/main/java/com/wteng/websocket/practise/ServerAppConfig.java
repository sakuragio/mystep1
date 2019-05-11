package com.wteng.websocket.practise;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangteng on 2019/5/11.
 */
public class ServerAppConfig implements ServerApplicationConfig {
    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> set) {
        Set mySet = new HashSet<ServerEndpointConfig>();
        ServerEndpointConfig serverEndpointConfig = ServerEndpointConfig.Builder.create(SimpleSocket2.class,
                "/echo2").build();
        mySet.add(serverEndpointConfig);
        return mySet;
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        return scanned;
    }
}
