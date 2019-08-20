package com.anlsj.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

public class CustomRuleByMySelf extends AbstractLoadBalancerRule {

    //仍然使用轮询算法，更改项：每个服务调用五次之后再轮询下一个服务

    private int total = 0;
    private int currentIndex = 0;

    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }

            List<Server> up_list = lb.getReachableServers();
            List<Server> all_list = lb.getAllServers();

            int server_count = all_list.size();
            if (server_count == 0) {
                return null;
            }

            if (total < 5) {
                server = up_list.get(currentIndex);
                total++;
            } else {
                total = 0;
                currentIndex++;
                if (currentIndex >= up_list.size()) {
                    currentIndex = 0;
                }
            }

            if (server == null) {
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            server = null;
            Thread.yield();
        }
        return server;
    }
}
