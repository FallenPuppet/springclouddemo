package com.anlsj.springcloud.controller;

import com.anlsj.springcloud.entities.Dept;
import com.anlsj.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.assertj.core.internal.bytebuddy.implementation.bytecode.Throw;
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
    @HystrixCommand(fallbackMethod = "processHystrix_GET")
    public Dept get(@PathVariable Long id) {
        System.out.println("这里访问到了");
        Dept dept = deptService.get(id);
        if (dept == null) {
            System.out.println("到这里了");
            throw new RuntimeException("该ID：" + id + "没有找到对应的信息");
        }
        return dept;
    }

    public Dept processHystrix_GET(@PathVariable("id") Long id) {
        Dept dept = new Dept();
        dept.setDeptno(id).setDname("该ID" + id + "没有对应的信息，null 来自@HystrixCommand").setDb_source("There is no this database in MYSQL");
        return dept;
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
