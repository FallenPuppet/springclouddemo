package com.anlsj.springcloud.service;

import com.anlsj.springcloud.entities.Dept;

import java.util.List;

public interface DeptService {

    boolean add(Dept dept);

    Dept get(Long id);

    List<Dept> list();
}
