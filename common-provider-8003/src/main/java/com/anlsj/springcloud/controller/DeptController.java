package com.anlsj.springcloud.controller;

import com.anlsj.springcloud.entities.Dept;
import com.anlsj.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {


    @Autowired
    private DeptService deptService;
    @Autowired
    private DiscoveryClient client;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept) {
        return deptService.add(dept);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable Long id) {
        return deptService.get(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Dept> list() {
        return deptService.list();
    }

    @RequestMapping(value = "/discovery", method = RequestMethod.GET)
    public Object discovery() {
        List<String> list = client.getServices();
        System.out.println("*************************" + list);
        List<ServiceInstance> srvList = client.getInstances("MICROSERVICE-DEPT");
        for (ServiceInstance ele : srvList) {
            System.out.println(ele.getServiceId() + "\t" + ele.getHost() + "\t" + ele.getPort() + "\t" + ele.getUri());
        }
        return this.client;
    }
}
