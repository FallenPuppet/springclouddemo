package com.anlsj.springcloud.controller;

import com.anlsj.springcloud.entities.Dept;
import com.anlsj.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

//    //private static final String PREFIX_URL = "http://localhost:8001";
//    private static final String PREFIX_URL = "http://MICROSERVICE-DEPT";
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @RequestMapping(value = "/consumer/dept/add")
//    public boolean add(Dept dept) {
//        return restTemplate.postForObject(PREFIX_URL + "/dept/add", dept, Boolean.class);
//    }
//
//    @RequestMapping(value = "/consumer/dept/get/{id}")
//    public Dept get(@PathVariable("id") Long id) {
//        return restTemplate.getForObject(PREFIX_URL + "/dept/get/" + id, Dept.class);
//    }
//
//    @RequestMapping(value = "/consumer/dept/list")
//    public List<Dept> list() {
//        return restTemplate.getForObject(PREFIX_URL + "/dept/list", List.class);
//    }


    //引入Feign之后改写Controller

    @Autowired
    private DeptClientService deptClientService = null;


    @RequestMapping(value = "/comsumer/dept/get/{id}")
    public Dept get(@PathVariable("id") long id) {
        return deptClientService.get(id);
    }

    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> list() {
        return deptClientService.list();
    }

    @RequestMapping(value = "/comsumer/dept/add")
    public boolean add(Dept dept) {
        return deptClientService.add(dept);
    }

}
